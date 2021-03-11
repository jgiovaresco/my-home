package cqrs.command;

public interface CommandMiddleware {
    default void beforeExecution(Command command) {}

    default void onError(Throwable error) {}

    default void afterExecution(Command command, CommandResponse<?> response) {}
}
