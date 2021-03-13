package my.home.bc.library.query;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.github.javafaker.Faker;
import infrastructure.ddd.UuidGenerator;
import java.util.List;
import java.util.stream.IntStream;
import my.home.bc.library.error.LibraryNotFoundException;
import my.home.bc.library.infrastructure.projection.BooksProjectionInMemory;
import my.home.bc.library.infrastructure.repository.LibraryRepositoryInMemory;
import my.home.bc.library.model.Book;
import my.home.bc.library.model.Library;
import my.home.bc.library.model.NewBookAdded;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

class GetAllBooksQueryHandlerTest {

    private static final UuidGenerator generator = new UuidGenerator();

    private static final String LIBRARY_ID = generator.generateId();
    private static final String OWNER_ID = "auth0|1234567890";

    private LibraryRepositoryInMemory libraryRepository;
    private BooksProjectionInMemory booksProjection;
    private GetAllBooksQueryHandler handler;

    @BeforeEach
    void setUp() {
        libraryRepository = new LibraryRepositoryInMemory();
        booksProjection = new BooksProjectionInMemory();
        handler = new GetAllBooksQueryHandler(libraryRepository, booksProjection);
    }

    @Test
    void returns_library_books() {
        aLibrary();
        var existingBooks = fewBooks();
        Flux.fromIterable(existingBooks).map(booksProjection::update).blockLast();

        var books = handler.execute(aQuery()).blockOptional();

        Assertions
            .assertThat(books)
            .isPresent()
            .get()
            .extracting(Books::getRows)
            .asList()
            .hasSize(existingBooks.size())
            .containsAll(existingBooks.stream().map(Book::from).collect(toList()));
    }

    @Test
    void throws_when_no_library_found() {
        var query = aQuery();

        assertThatThrownBy(() -> handler.execute(query).block()).isInstanceOf(LibraryNotFoundException.class);
    }

    private GetAllBooksQuery aQuery() {
        return new GetAllBooksQuery(OWNER_ID);
    }

    private Library aLibrary() {
        var library = Library.builder().id(LIBRARY_ID).ownerId(OWNER_ID).build();
        libraryRepository.add(library);
        return library;
    }

    private List<NewBookAdded> fewBooks() {
        Faker faker = new Faker();
        return IntStream
            .range(1, 5)
            .boxed()
            .map(
                index ->
                    NewBookAdded
                        .builder()
                        .libraryId(LIBRARY_ID)
                        .isbn(faker.code().isbn13())
                        .title(faker.book().title())
                        .author(faker.book().author())
                        .build()
            )
            .collect(toList());
    }
}
