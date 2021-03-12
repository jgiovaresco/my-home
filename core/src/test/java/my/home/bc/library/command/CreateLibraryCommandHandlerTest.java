package my.home.bc.library.command;

import static my.home.bc.library.assertion.Assertions.assertThat;

import cqrs.command.CommandResponse;
import infrastructure.ddd.UuidGenerator;
import my.home.bc.library.infrastructure.repository.LibraryRepositoryInMemory;
import my.home.bc.library.model.LibraryCreated;
import my.home.bc.library.repository.LibraryRepository;
import my.home.infrastructure.FakeGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CreateLibraryCommandHandlerTest {

    private static final UuidGenerator generator = new UuidGenerator();

    private static final String LIBRARY_ID = generator.generateId();
    private static final String ACCOUNT_ID = generator.generateId();
    private static final String OWNER_ID = "auth0|1234567890";

    private CreateLibraryCommandHandler handler;

    @BeforeEach
    void setUp() {
        LibraryRepository libraryRepository = new LibraryRepositoryInMemory();
        FakeGenerator fakeGenerator = new FakeGenerator(LIBRARY_ID);
        handler = new CreateLibraryCommandHandler(fakeGenerator, libraryRepository);
    }

    @Test
    void saves_the_registered_account() {
        var registration = aCreateLibraryCommand();

        var response = handler.execute(registration).blockOptional();
        var responseAssert = Assertions.assertThat(response).isPresent().get();

        responseAssert
            .extracting(CommandResponse::getResponse)
            .satisfies(account -> assertThat(account).hasId(LIBRARY_ID).hasAccountId(ACCOUNT_ID).hasOwnerId(OWNER_ID));

        responseAssert
            .extracting(CommandResponse::getEvents)
            .asList()
            .hasSize(1)
            .first()
            .isInstanceOf(LibraryCreated.class);
    }

    private CreateLibraryCommand aCreateLibraryCommand() {
        return CreateLibraryCommand.builder().accountId(ACCOUNT_ID).ownerId(OWNER_ID).build();
    }
}
