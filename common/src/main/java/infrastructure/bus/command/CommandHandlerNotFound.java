package infrastructure.bus.command;

import lombok.Getter;

@Getter
public class CommandHandlerNotFound extends RuntimeException {

    private final String command;

    public CommandHandlerNotFound(String command) {
        super("COMMAND_HANDLER_NOT_FOUND");
        this.command = command;
    }
}
