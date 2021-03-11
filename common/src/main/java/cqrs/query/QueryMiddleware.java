package cqrs.query;

public interface QueryMiddleware {
    default void beforeExecution(Query query) {}

    default void onError() {}

    default void afterExecution() {}
}
