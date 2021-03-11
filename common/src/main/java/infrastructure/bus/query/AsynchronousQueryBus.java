package infrastructure.bus.query;

import cqrs.query.Query;
import cqrs.query.QueryBus;
import cqrs.query.QueryHandler;
import cqrs.query.QueryMiddleware;
import java.util.*;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@SuppressWarnings("rawtypes")
public class AsynchronousQueryBus implements QueryBus {

    protected Map<Class<?>, QueryHandler> handlers = new HashMap<>();
    protected List<QueryMiddleware> middlewares = new ArrayList<>();

    public AsynchronousQueryBus(Set<? extends QueryMiddleware> middlewares, Set<? extends QueryHandler> handlers) {
        this.middlewares.addAll(middlewares);
        handlers.forEach(handler -> this.handlers.put(handler.queryType(), handler));
    }

    @Override
    public <TResponse> Mono<TResponse> send(Query<TResponse> query) {
        return this.dispatch(query);
    }

    private <TResponse> Mono<TResponse> dispatch(Query<TResponse> query) {
        final QueryHandler<Query<TResponse>, TResponse> handler = this.handlers.get(query.getClass());

        if (handler == null) {
            log.warn("No handler found for {}", query.getClass());
            return Mono.error(new QueryHandlerNotFound(query.getClass().getSimpleName()));
        }

        return Mono
            .defer(() -> handler.execute(query))
            .doOnSubscribe(d -> middlewares.forEach(m -> m.beforeExecution(query)))
            .doOnSuccess(r -> middlewares.forEach(m -> m.afterExecution()))
            .doOnError(r -> middlewares.forEach(m -> m.onError()));
    }
}
