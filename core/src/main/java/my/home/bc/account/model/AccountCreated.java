package my.home.bc.account.model;

import cqrs.event.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AccountCreated implements Event {

    private final String id;
    private final String externalId;
    private final String email;
    private final String firstName;
    private final String lastName;
}
