package infrastructure.bus.event;

import lombok.Getter;

@Getter
public class EventHandlerNotFound extends RuntimeException {

    private final String event;

    public EventHandlerNotFound(String event) {
        super("EVENT_HANDLER_NOT_FOUND");
        this.event = event;
    }
}
