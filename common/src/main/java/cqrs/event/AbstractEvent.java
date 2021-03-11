package cqrs.event;

import dates.Dates;
import lombok.Getter;

@Getter
public class AbstractEvent implements Event {

    private final long timestamp = Dates.instantNow().toEpochMilli();
}
