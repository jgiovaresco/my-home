package my.home.bc.library.port.in.web;

import static io.restassured.RestAssured.*;
import static java.util.stream.Collectors.toList;
import static my.home.bc.library.assertions.Assertions.assertThat;
import static org.hamcrest.Matchers.startsWith;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import java.util.List;
import java.util.stream.IntStream;
import my.home.bc.library.model.Library;
import my.home.bc.library.port.in.web.model.BookDto;
import my.home.bc.library.port.in.web.model.BooksDto;
import my.home.bc.library.port.in.web.model.NewBookDto;
import my.home.bc.library.repository.LibraryRepository;
import my.home.infrastructure.web.AbstractIntegrationTest;
import my.home.infrastructure.web.WithToken;
import my.home.infrastructure.web.exception.ErrorResponse;
import my.home.infrastructure.web.exception.ErrorResponseAssert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Mono;

class BooksResourceTest extends AbstractIntegrationTest {

    private static final String EMAIL = "john.doe@gmail.com";
    private static final String OWNER_ID = "auth0|123123123";

    private static final String ISBN = "9781617295621";
    private static final String TITLE = "Vert.x in Action";
    private static final String AUTHOR = "Julien Ponge";

    @LocalServerPort
    private int port;

    @Autowired
    private Faker faker;

    @Autowired
    private LibraryRepository libraryRepository;

    private String baseUrl;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        RestAssured.basePath = "/api";
        //        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        baseUrl = "http://localhost:" + port;
    }

    @Nested
    @DisplayName("POST /books")
    class AddNewBook {

        @Test
        @WithToken(email = EMAIL, sub = OWNER_ID)
        void add_a_new_book_to_the_current_user_library() {
            aLibrary().blockOptional();
            var newBook = NewBookDto.builder().isbn(ISBN).author(AUTHOR).title(TITLE).build();

            var dto = given()
                .contentType("application/json")
                .body(newBook)
                .when()
                .post("/books")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .header("location", startsWith(baseUrl + "/api/books/" + ISBN))
                .extract()
                .as(BookDto.class);

            assertThat(dto).hasIsbn(ISBN).hasTitle(TITLE).hasAuthor(AUTHOR);
        }

        @Test
        @WithToken(email = EMAIL)
        void rejects_when_library_does_not_exist() {
            var newBook = NewBookDto.builder().isbn(ISBN).author(AUTHOR).title(TITLE).build();

            ErrorResponse error = given()
                .contentType("application/json")
                .body(newBook)
                .when()
                .post("/books")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .extract()
                .as(ErrorResponse.class);

            new ErrorResponseAssert(error).hasStatus(HttpStatus.NOT_FOUND.value()).hasMessage("LIBRARY_NOT_FOUND");
        }

        @Test
        @WithToken(email = EMAIL, sub = OWNER_ID)
        void rejects_when_adding_existing_book() {
            aLibrary().blockOptional();
            var newBook = NewBookDto.builder().isbn(faker.code().isbn13()).author(AUTHOR).title(TITLE).build();

            expect().statusCode(201).given().contentType("application/json").body(newBook).when().post("/books");

            ErrorResponse error = given()
                .contentType("application/json")
                .body(newBook)
                .when()
                .post("/books")
                .then()
                .statusCode(HttpStatus.CONFLICT.value())
                .extract()
                .as(ErrorResponse.class);

            new ErrorResponseAssert(error).hasStatus(HttpStatus.CONFLICT.value()).hasMessage("EXISTING_BOOK");
        }
    }

    @Nested
    @DisplayName("GET /books")
    class GetAllBooks {

        @Test
        @WithToken(email = EMAIL, sub = OWNER_ID)
        void get_all_books_of_the_current_user_library() {
            aLibrary().blockOptional();
            var books = fewBooks()
                .stream()
                .map(
                    newBook ->
                        given()
                            .contentType("application/json")
                            .body(newBook)
                            .when()
                            .post("/books")
                            .then()
                            .extract()
                            .as(BookDto.class)
                )
                .collect(toList());

            var response = when().get("/books").then().statusCode(HttpStatus.OK.value()).extract().as(BooksDto.class);

            Assertions.assertThat(response).extracting(BooksDto::getRows).asList().containsAll(books);
        }

        @Test
        @WithToken(email = EMAIL)
        void rejects_when_library_does_not_exist() {
            var newBook = NewBookDto.builder().isbn(ISBN).author(AUTHOR).title(TITLE).build();

            ErrorResponse error = given()
                .contentType("application/json")
                .body(newBook)
                .when()
                .get("/books")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .extract()
                .as(ErrorResponse.class);

            new ErrorResponseAssert(error).hasStatus(HttpStatus.NOT_FOUND.value()).hasMessage("LIBRARY_NOT_FOUND");
        }
    }

    private Mono<Boolean> aLibrary() {
        var library = Library.builder().id("library#1").accountId("account#1").ownerId(OWNER_ID).build();
        return libraryRepository.add(library);
    }

    private List<NewBookDto> fewBooks() {
        Faker faker = new Faker();
        return IntStream
            .range(1, 5)
            .boxed()
            .map(
                index ->
                    NewBookDto
                        .builder()
                        .isbn(faker.code().isbn13())
                        .title(faker.book().title())
                        .author(faker.book().author())
                        .build()
            )
            .collect(toList());
    }
}
