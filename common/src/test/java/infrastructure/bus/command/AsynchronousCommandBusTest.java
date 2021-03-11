package infrastructure.bus.command;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import cqrs.command.Command;
import cqrs.command.CommandHandler;
import cqrs.command.CommandMiddleware;
import cqrs.command.CommandResponse;
import cqrs.event.Event;
import cqrs.event.EventBus;
import java.util.Set;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

class AsynchronousCommandBusTest {

    @Test
    void executes_a_command() {
        var handler = anHandler();
        var bus = busWith(handler);
        var command = aCommand();

        bus.send(command).block();

        assertThat(handler.receivedCommand).isSameAs(command);
    }

    @Test
    void returns_the_command_result() {
        var handler = anHandler();
        var bus = busWith(handler);
        var command = aCommand();

        var result = bus.send(command).block();

        assertThat(result).isNotNull().extracting(CommandResponse::getResponse).isEqualTo("42");
    }

    @Test
    void sends_command_events_after_execution() {
        var eventBus = mock(EventBus.class);
        when(eventBus.send(any())).thenReturn(Mono.empty());

        var handler = anHandler();
        var bus = busWith(handler, eventBus);
        var command = aCommand();

        var result = bus.send(command).block();

        assert result != null;
        result.getEvents().forEach(e -> verify(eventBus).send(e));
    }

    @Test
    void raise_error_when_empty() {
        var bus = emptyBus();

        assertThatThrownBy(() -> bus.send(new FakeCommand()).block()).isInstanceOf(CommandHandlerNotFound.class);
    }

    @Test
    void runs_middleware_around_execution() {
        var middleware = mock(CommandMiddleware.class);
        var bus = busWith(middleware);
        var command = aCommand();

        var result = bus.send(command).block();

        verify(middleware).beforeExecution(command);
        verify(middleware).afterExecution(command, result);
    }

    @Test
    void runs_middleware_around_error_execution() {
        var handler = anHandler().throwsException();
        var middleware = mock(CommandMiddleware.class);
        var bus = busWith(handler, middleware);

        Assertions.assertThatThrownBy(() -> bus.send(aCommand()).block()).isInstanceOf(RuntimeException.class);

        verify(middleware).onError(any(RuntimeException.class));
    }

    //region Bus builders
    private AsynchronousCommandBus emptyBus() {
        return new AsynchronousCommandBus(Set.of(), Set.of());
    }

    private AsynchronousCommandBus busWith(FakeCommandHandler handler, CommandMiddleware middleware) {
        return new AsynchronousCommandBus(Set.of(middleware), Set.of(handler));
    }

    private AsynchronousCommandBus busWith(
        FakeCommandHandler handler,
        CommandMiddleware middleware,
        EventBus eventBus
    ) {
        return new AsynchronousCommandBus(Set.of(middleware), Set.of(handler), eventBus);
    }

    private AsynchronousCommandBus busWith(CommandMiddleware middleware) {
        return busWith(anHandler(), middleware);
    }

    private AsynchronousCommandBus busWith(FakeCommandHandler handler) {
        return busWith(handler, mock(CommandMiddleware.class));
    }

    private AsynchronousCommandBus busWith(FakeCommandHandler handler, EventBus eventBus) {
        return busWith(handler, mock(CommandMiddleware.class), eventBus);
    }

    //endregion

    //region FakeCommand
    private FakeCommand aCommand() {
        return new FakeCommand();
    }

    private FakeCommandHandler anHandler() {
        return new FakeCommandHandler();
    }

    private class FakeCommand implements Command {}

    private class FakeEvent implements Event {}

    private class FakeCommandHandler implements CommandHandler<FakeCommand, String> {

        private FakeCommand receivedCommand;
        private boolean exception;

        @Override
        public Mono<CommandResponse<String>> execute(FakeCommand command) {
            receivedCommand = command;

            if (exception) {
                return Mono.error(new RuntimeException("this is an error"));
            }

            return Mono.just(new CommandResponse<>("42", new FakeEvent()));
        }

        @Override
        public Class<FakeCommand> commandType() {
            return FakeCommand.class;
        }

        FakeCommandHandler throwsException() {
            this.exception = true;
            return this;
        }
    }
    //endregion
}
