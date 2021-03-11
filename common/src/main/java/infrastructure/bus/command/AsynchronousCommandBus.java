package infrastructure.bus.command;

import cqrs.command.*;
import cqrs.event.EventBus;
import java.util.*;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@SuppressWarnings("rawtypes")
public class AsynchronousCommandBus implements CommandBus {

    protected Map<Class<?>, CommandHandler> handlers = new HashMap<>();
    protected List<CommandMiddleware> middlewares = new ArrayList<>();
    private EventBus eventBus;

    public AsynchronousCommandBus(
        Set<? extends CommandMiddleware> middlewares,
        Set<? extends CommandHandler> handlers
    ) {
        this(middlewares, handlers, null);
    }

    public AsynchronousCommandBus(
        Set<? extends CommandMiddleware> middlewares,
        Set<? extends CommandHandler> handlers,
        EventBus eventBus
    ) {
        this.middlewares.addAll(middlewares);
        handlers.forEach(handler -> this.handlers.put(handler.commandType(), handler));
        this.eventBus = eventBus;
    }

    @Override
    public <TResponse> Mono<CommandResponse<TResponse>> send(Command command) {
        return this.dispatch(command);
    }

    private <TResponse> Mono<CommandResponse<TResponse>> dispatch(Command command) {
        final CommandHandler<Command, TResponse> handler = this.handlers.get(command.getClass());

        if (handler == null) {
            log.warn("No handler found for {}", command.getClass());
            return Mono.error(new CommandHandlerNotFound(command.getClass().getSimpleName()));
        }

        return Mono
            .defer(() -> handler.execute(command))
            .flatMap(this::dispatchEvents)
            .doOnSubscribe(d -> middlewares.forEach(m -> m.beforeExecution(command)))
            .doOnSuccess(response -> middlewares.forEach(m -> m.afterExecution(command, response)))
            .doOnError(error -> middlewares.forEach(m -> m.onError(error)));
    }

    private <TResponse> Mono<CommandResponse<TResponse>> dispatchEvents(CommandResponse<TResponse> response) {
        if (null != eventBus) {
            return Flux
                .fromIterable(response.getEvents())
                .flatMap(e -> eventBus.send(e))
                .collectList()
                .thenReturn(response);
        }
        return Mono.just(response);
    }
}
