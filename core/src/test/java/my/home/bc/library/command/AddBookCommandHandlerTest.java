package my.home.bc.library.command;

import static my.home.bc.library.assertion.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import cqrs.command.CommandResponse;
import infrastructure.ddd.UuidGenerator;
import my.home.bc.library.error.ExistingBookException;
import my.home.bc.library.error.LibraryNotFoundException;
import my.home.bc.library.infrastructure.repository.LibraryRepositoryInMemory;
import my.home.bc.library.model.Book;
import my.home.bc.library.model.Library;
import my.home.bc.library.model.NewBookAdded;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AddBookCommandHandlerTest {

    private static final UuidGenerator generator = new UuidGenerator();

    private static final String LIBRARY_ID = generator.generateId();
    private static final String OWNER_ID = "auth0|1234567890";
    private static final String ISBN = "9781617295621";
    private static final String TITLE = "Vert.x in Action";
    private static final String AUTHOR = "Julien Ponge";

    private LibraryRepositoryInMemory libraryRepository;

    private AddBookCommandHandler handler;

    @BeforeEach
    void setUp() {
        libraryRepository = new LibraryRepositoryInMemory();
        handler = new AddBookCommandHandler(libraryRepository);
    }

    @Test
    void returns_the_new_book() {
        aLibrary();
        var command = anAddBookCommand();

        var response = handler.execute(command).blockOptional();
        var responseAssert = Assertions.assertThat(response).isPresent().get();

        responseAssert
            .extracting(CommandResponse::getResponse)
            .satisfies(book -> assertThat(book).hasIsbn(ISBN).hasAuthor(AUTHOR).hasTitle(TITLE));

        responseAssert
            .extracting(CommandResponse::getEvents)
            .asList()
            .hasSize(1)
            .first()
            .isInstanceOf(NewBookAdded.class);
    }

    @Test
    void adds_the_new_book_in_library() {
        var library = aLibrary();
        var command = anAddBookCommand();

        handler.execute(command).blockOptional();

        Book expectedBook = Book.builder().isbn(ISBN).title(TITLE).author(AUTHOR).build();
        assertThat(library).containsBook(expectedBook);
    }

    @Test
    void throws_when_no_library_found() {
        var command = anAddBookCommand();

        assertThatThrownBy(() -> handler.execute(command).block()).isInstanceOf(LibraryNotFoundException.class);
    }

    @Test
    void throws_when_adding_existing_book() {
        var library = aLibrary();
        var command = anAddBookCommand();

        handler.execute(command).block();
        assertThatThrownBy(() -> handler.execute(command).block()).isInstanceOf(ExistingBookException.class);
    }

    private Library aLibrary() {
        var library = Library.builder().id(LIBRARY_ID).ownerId(OWNER_ID).build();
        libraryRepository.add(library);
        return library;
    }

    private AddBookCommand anAddBookCommand() {
        return AddBookCommand.builder().ownerId(OWNER_ID).author(AUTHOR).isbn(ISBN).title(TITLE).build();
    }
}
