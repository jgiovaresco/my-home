package cqrs.command;

import cqrs.event.Event;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.Getter;

@Getter
public class CommandResponse<TResponse> {

    private final TResponse response;
    private final List<? extends Event> events;

    public CommandResponse(TResponse response) {
        this.response = response;
        this.events = new ArrayList<>();
    }

    public CommandResponse(TResponse response, Event... events) {
        this.response = response;
        this.events = new ArrayList<>(Arrays.asList(events));
    }
}
