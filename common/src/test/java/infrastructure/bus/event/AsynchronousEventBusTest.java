package infrastructure.bus.event;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import cqrs.event.AbstractEvent;
import cqrs.event.EventHandler;
import cqrs.event.EventMiddleware;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

class AsynchronousEventBusTest {

    @Test
    void runs_all_handlers() {
        var handler1 = anHandler();
        var handler2 = anHandler();
        var bus = busWith(List.of(handler1, handler2));
        var event = anEvent();

        bus.send(event).block();

        assertThat(handler1.receivedEvent).isSameAs(event);
        assertThat(handler2.receivedEvent).isSameAs(event);
    }

    @Test
    void runs_middleware_around_execution() {
        var middleware = mock(EventMiddleware.class);
        var bus = busWith(middleware);
        var event = anEvent();

        bus.send(event).block();

        verify(middleware).beforeExecution(event);
        verify(middleware).afterExecution();
    }

    @Test
    void runs_middleware_around_error_execution() {
        var handler = anHandler().throwsException();
        var middleware = mock(EventMiddleware.class);
        var bus = busWith(List.of(handler), middleware);

        Assertions.assertThatThrownBy(() -> bus.send(anEvent()).block()).isInstanceOf(RuntimeException.class);

        verify(middleware).onError();
    }

    //region Bus builders
    private AsynchronousEventBus emptyBus() {
        return new AsynchronousEventBus(Set.of(), Set.of());
    }

    private AsynchronousEventBus busWith(List<FakeEventHandler> handlers, EventMiddleware middleware) {
        return new AsynchronousEventBus(Set.of(middleware), new HashSet<>(handlers));
    }

    private AsynchronousEventBus busWith(EventMiddleware middleware) {
        return busWith(List.of(anHandler()), middleware);
    }

    private AsynchronousEventBus busWith(List<FakeEventHandler> handlers) {
        return busWith(handlers, mock(EventMiddleware.class));
    }

    //endregion

    //region FakeEvent
    private FakeEvent anEvent() {
        return new FakeEvent();
    }

    private FakeEventHandler anHandler() {
        return new FakeEventHandler();
    }

    private class FakeEvent extends AbstractEvent {}

    private class FakeEventHandler implements EventHandler<FakeEvent> {

        private FakeEvent receivedEvent;
        private boolean exception;

        @Override
        public Mono<Void> execute(FakeEvent event) {
            receivedEvent = event;

            if (exception) {
                return Mono.error(new RuntimeException("this is an error"));
            }

            return Mono.empty();
        }

        @Override
        public Class<AsynchronousEventBusTest.FakeEvent> eventType() {
            return FakeEvent.class;
        }

        AsynchronousEventBusTest.FakeEventHandler throwsException() {
            this.exception = true;
            return this;
        }
    }
    //endregion
}
