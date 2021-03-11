package cqrs.query;

import reactor.core.publisher.Mono;

public interface QueryBus {
    <TResponse> Mono<TResponse> send(Query<TResponse> query);
}
