package my.home.bc.library.model;

import cqrs.event.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LibraryCreated implements Event {

    private final String id;
    private final String accountId;
    private final String ownerId;
}
