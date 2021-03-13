package my.home.bc.library.query;

import cqrs.query.Query;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GetAllBooksQuery implements Query<Books> {

    @NotNull
    private final String ownerId;
}
