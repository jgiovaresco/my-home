package cqrs.query;

import reactor.core.publisher.Mono;

public interface QueryHandler<TQuery extends Query<TResponse>, TResponse> {
    Mono<TResponse> execute(TQuery query);
    Class<TQuery> queryType();
}
