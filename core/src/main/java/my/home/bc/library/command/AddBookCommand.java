package my.home.bc.library.command;

import cqrs.command.Command;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddBookCommand implements Command {

    @NotNull
    private final String ownerId;

    @NotNull
    private final String isbn;

    @NotNull
    private final String title;

    @NotNull
    private final String author;
}
