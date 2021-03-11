package cqrs.command;

import reactor.core.publisher.Mono;

public interface CommandBus {
    <TResponse> Mono<CommandResponse<TResponse>> send(Command command);
}
