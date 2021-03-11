package infrastructure.bus.event;

import cqrs.event.*;
import java.util.*;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@SuppressWarnings("rawtypes")
public class AsynchronousEventBus implements EventBus {

    protected Map<Class<?>, List<EventHandler>> handlers = new HashMap<>();
    protected List<EventMiddleware> middlewares = new ArrayList<>();

    public AsynchronousEventBus(Set<? extends EventMiddleware> middlewares, Set<? extends EventHandler> handlers) {
        this.middlewares.addAll(middlewares);
        handlers.forEach(
            handler -> {
                this.handlers.putIfAbsent(handler.eventType(), new ArrayList<>());
                this.handlers.get(handler.eventType()).add(handler);
            }
        );
    }

    @Override
    public Mono<Void> send(Event event) {
        return this.dispatch(event);
    }

    private Mono<Void> dispatch(Event event) {
        final List<EventHandler> handlers = this.handlers.getOrDefault(event.getClass(), new ArrayList<>());

        if (handlers.isEmpty()) {
            log.warn("No handler found for {}", event.getClass());
        }

        return Flux
            .fromIterable(handlers)
            .concatMap(h -> h.execute(event))
            .doOnSubscribe(d -> middlewares.forEach(m -> m.beforeExecution(event)))
            .collectList()
            .doOnSuccess(r -> middlewares.forEach(EventMiddleware::afterExecution))
            .doOnError(r -> middlewares.forEach(EventMiddleware::onError));
    }
}
