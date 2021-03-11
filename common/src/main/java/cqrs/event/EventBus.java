package cqrs.event;

import reactor.core.publisher.Mono;

public interface EventBus {
    Mono<Void> send(Event event);
}
