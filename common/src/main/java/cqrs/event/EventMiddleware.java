package cqrs.event;

public interface EventMiddleware {
    default void beforeExecution(Event event) {}

    default void onError() {}

    default void afterExecution() {}
}
