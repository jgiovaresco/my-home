package my.home.bc.library.model;

import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter(AccessLevel.PRIVATE)
public class Library {

    private String id;
    private String accountId;
    private String ownerId;
}
