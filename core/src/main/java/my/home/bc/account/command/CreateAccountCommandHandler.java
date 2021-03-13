package my.home.bc.account.command;

import cqrs.command.CommandHandler;
import cqrs.command.CommandResponse;
import ddd.IdGenerator;
import lombok.extern.slf4j.Slf4j;
import my.home.bc.account.error.ExistingAccountException;
import my.home.bc.account.model.Account;
import my.home.bc.account.model.AccountCreated;
import my.home.bc.account.repository.AccountRepository;
import reactor.core.publisher.Mono;

@Slf4j
public class CreateAccountCommandHandler implements CommandHandler<CreateAccountCommand, Account> {

    private final IdGenerator generator;
    private final AccountRepository accountRepository;

    public CreateAccountCommandHandler(IdGenerator generator, AccountRepository accountRepository) {
        this.generator = generator;
        this.accountRepository = accountRepository;
    }

    @Override
    public Class<CreateAccountCommand> commandType() {
        return CreateAccountCommand.class;
    }

    @Override
    public Mono<CommandResponse<Account>> execute(CreateAccountCommand command) {
        var account = Account
            .builder()
            .id(this.generator.generateId())
            .externalId(command.getExternalId())
            .email(command.getEmail())
            .firstName(command.getFirstname())
            .lastName(command.getLastname())
            .build();
        var accountCreated = new AccountCreated(
            account.getId(),
            account.getExternalId(),
            account.getEmail(),
            account.getFirstName(),
            account.getLastName()
        );

        return accountRepository
            .findByEmail(command.getEmail())
            .flatMap(existing -> Mono.error(new ExistingAccountException()))
            .switchIfEmpty(Mono.defer(() -> accountRepository.add(account)))
            .map(unused -> new CommandResponse<>(account, accountCreated));
    }
}
