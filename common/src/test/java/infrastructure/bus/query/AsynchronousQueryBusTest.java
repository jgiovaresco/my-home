package infrastructure.bus.query;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import cqrs.query.Query;
import cqrs.query.QueryHandler;
import cqrs.query.QueryMiddleware;
import java.util.Set;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

class AsynchronousQueryBusTest {

    @Test
    void executes_a_query() {
        var handler = anHandler();
        var bus = busWith(handler);
        var query = aQuery();

        bus.send(query).block();

        assertThat(handler.receivedQuery).isSameAs(query);
    }

    @Test
    void returns_the_query_result() {
        var handler = anHandler();
        var bus = busWith(handler);
        var query = aQuery();

        var result = bus.send(query).block();

        assertThat(result).isNotNull().isEqualTo("42");
    }

    @Test
    void raise_error_when_empty() {
        var bus = emptyBus();

        assertThatThrownBy(() -> bus.send(new FakeQuery()).block()).isInstanceOf(QueryHandlerNotFound.class);
    }

    @Test
    void runs_middleware_around_execution() {
        var middleware = mock(QueryMiddleware.class);
        var bus = busWith(middleware);
        var query = aQuery();

        bus.send(query).block();

        verify(middleware).beforeExecution(query);
        verify(middleware).afterExecution();
    }

    @Test
    void runs_middleware_around_error_execution() {
        var handler = anHandler().throwsException();
        var middleware = mock(QueryMiddleware.class);
        var bus = busWith(handler, middleware);

        Assertions.assertThatThrownBy(() -> bus.send(aQuery()).block()).isInstanceOf(RuntimeException.class);

        verify(middleware).onError();
    }

    //region Bus builders
    private AsynchronousQueryBus emptyBus() {
        return new AsynchronousQueryBus(Set.of(), Set.of());
    }

    private AsynchronousQueryBus busWith(FakeQueryHandler handler, QueryMiddleware middleware) {
        return new AsynchronousQueryBus(Set.of(middleware), Set.of(handler));
    }

    private AsynchronousQueryBus busWith(QueryMiddleware middleware) {
        return busWith(anHandler(), middleware);
    }

    private AsynchronousQueryBus busWith(FakeQueryHandler handler) {
        return busWith(handler, mock(QueryMiddleware.class));
    }

    //endregion

    //region FakeQuery
    private FakeQuery aQuery() {
        return new FakeQuery();
    }

    private FakeQueryHandler anHandler() {
        return new FakeQueryHandler();
    }

    private class FakeQuery implements Query<String> {}

    private class FakeQueryHandler implements QueryHandler<FakeQuery, String> {

        private FakeQuery receivedQuery;
        private boolean exception;

        @Override
        public Mono<String> execute(FakeQuery query) {
            receivedQuery = query;

            if (exception) {
                return Mono.error(new RuntimeException("this is an error"));
            }

            return Mono.just("42");
        }

        @Override
        public Class<FakeQuery> queryType() {
            return FakeQuery.class;
        }

        FakeQueryHandler throwsException() {
            this.exception = true;
            return this;
        }
    }
    //endregion
}
