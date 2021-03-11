package dates;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;

public class Dates {

    private static Dates INSTANCE = new Dates(Clock.systemUTC());

    private final Clock clock;

    Dates(Clock clock) {
        this.clock = clock;
    }

    public static void initialize(Dates instance) {
        INSTANCE = instance;
    }

    public static LocalDateTime now() {
        return LocalDateTime.now(INSTANCE.clock);
    }

    public static Instant instantNow() {
        return INSTANCE.clock.instant();
    }
}
