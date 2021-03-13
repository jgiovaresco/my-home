package my.home.bc.account.command;

import static my.home.bc.account.assertion.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import cqrs.command.CommandResponse;
import infrastructure.ddd.UuidGenerator;
import my.home.bc.account.error.ExistingAccountException;
import my.home.bc.account.infrastructure.AccountsRepositoryInMemory;
import my.home.bc.account.model.AccountCreated;
import my.home.bc.account.repository.AccountRepository;
import my.home.infrastructure.FakeGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CreateAccountCommandHandlerTest {

    private static final UuidGenerator generator = new UuidGenerator();

    private static final String ACCOUNT_ID = generator.generateId();
    private static final String FIRST_NAME = "John";
    private static final String LAST_NAME = "Doe";
    private static final String EMAIL = "john.doe@gmail.com";
    private static final String EXTERNAL_ID = "auth0|1234567890";

    private CreateAccountCommandHandler handler;

    @BeforeEach
    void setUp() {
        AccountRepository accountRepository = new AccountsRepositoryInMemory();
        FakeGenerator fakeGenerator = new FakeGenerator(ACCOUNT_ID);
        handler = new CreateAccountCommandHandler(fakeGenerator, accountRepository);
    }

    @Test
    void saves_the_registered_account() {
        var registration = aCreateAccountCommand();

        var response = handler.execute(registration).blockOptional();
        var responseAssert = Assertions.assertThat(response).isPresent().get();

        responseAssert
            .extracting(CommandResponse::getResponse)
            .satisfies(
                account ->
                    assertThat(account)
                        .hasId(ACCOUNT_ID)
                        .hasExternalId(EXTERNAL_ID)
                        .hasEmail(EMAIL)
                        .hasFirstName(FIRST_NAME)
                        .hasLastName(LAST_NAME)
            );

        responseAssert
            .extracting(CommandResponse::getEvents)
            .asList()
            .hasSize(1)
            .first()
            .isInstanceOf(AccountCreated.class);
    }

    @Test
    void throws_when_email_already_used() {
        var registration = aCreateAccountCommand();
        handler.execute(registration).block();

        assertThatThrownBy(() -> handler.execute(registration).block()).isInstanceOf(ExistingAccountException.class);
    }

    private CreateAccountCommand aCreateAccountCommand() {
        return CreateAccountCommand
            .builder()
            .email(EMAIL)
            .externalId(EXTERNAL_ID)
            .firstname(FIRST_NAME)
            .lastname(LAST_NAME)
            .build();
    }
}
