package my.home.bc.account.model;

import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter(AccessLevel.PRIVATE)
public class Account {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String externalId;
}
