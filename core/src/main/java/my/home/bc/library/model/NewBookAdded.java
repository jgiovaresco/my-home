package my.home.bc.library.model;

import cqrs.event.Event;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class NewBookAdded implements Event {

    private final String libraryId;
    private final String isbn;
    private final String title;
    private final String author;
}
