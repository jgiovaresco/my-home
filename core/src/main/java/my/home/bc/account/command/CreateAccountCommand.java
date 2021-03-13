package my.home.bc.account.command;

import cqrs.command.Command;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateAccountCommand implements Command {

    @NotNull
    private final String externalId;

    @NotNull
    private final String email;

    @NotNull
    private final String firstname;

    @NotNull
    private final String lastname;
}
