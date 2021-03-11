package cqrs.event;

import reactor.core.publisher.Mono;

public interface EventHandler<TEvent extends Event> {
    Mono<Void> execute(TEvent event);
    Class<TEvent> eventType();
}
