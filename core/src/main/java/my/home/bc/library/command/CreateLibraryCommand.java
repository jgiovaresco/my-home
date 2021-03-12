package my.home.bc.library.command;

import cqrs.command.Command;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateLibraryCommand implements Command {

    @NotNull
    private final String accountId;

    @NotNull
    private final String ownerId;
}
