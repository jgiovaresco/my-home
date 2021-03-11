package cqrs.command;

import reactor.core.publisher.Mono;

public interface CommandHandler<TCommand extends Command, TResponse> {
    Mono<CommandResponse<TResponse>> execute(TCommand command);
    Class<TCommand> commandType();
}
