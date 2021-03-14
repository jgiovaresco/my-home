package my.home.bc.library.port.in.web;

import cqrs.command.CommandBus;
import cqrs.command.CommandResponse;
import cqrs.query.QueryBus;
import java.security.Principal;
import my.home.bc.library.command.AddBookCommand;
import my.home.bc.library.model.Book;
import my.home.bc.library.port.in.web.model.BookDto;
import my.home.bc.library.port.in.web.model.BooksDto;
import my.home.bc.library.port.in.web.model.NewBookDto;
import my.home.bc.library.query.GetAllBooksQuery;
import my.home.infrastructure.web.security.Auth0JwtAuthenticationToken;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/books")
public class BooksResource {

    private final CommandBus commandBus;
    private final QueryBus queryBus;

    public BooksResource(CommandBus commandBus, QueryBus queryBus) {
        this.commandBus = commandBus;
        this.queryBus = queryBus;
    }

    @PostMapping
    public Mono<ResponseEntity<BookDto>> addNewBook(
        UriComponentsBuilder uriBuilder,
        @RequestBody NewBookDto newBookDto,
        Mono<Principal> principal
    ) {
        return principal
            .map(p -> new Auth0JwtAuthenticationToken((JwtAuthenticationToken) p))
            .map(token -> buildAddBookCommand(token.getSub(), newBookDto))
            .flatMap(commandBus::<Book>send)
            .map(CommandResponse::getResponse)
            .map(
                book ->
                    ResponseEntity
                        .created(uriBuilder.replacePath("/api/books/{id}").build(book.getIsbn()))
                        .body(BookDto.from(book))
            );
    }

    @GetMapping
    public Mono<BooksDto> getAllBooks(Mono<Principal> principal) {
        return principal
            .map(p -> new Auth0JwtAuthenticationToken((JwtAuthenticationToken) p))
            .map(token -> buildGetAllBooksQuery(token.getSub()))
            .flatMap(queryBus::send)
            .map(BooksDto::from);
    }

    private AddBookCommand buildAddBookCommand(String ownerId, NewBookDto newBookDto) {
        return AddBookCommand
            .builder()
            .ownerId(ownerId)
            .isbn(newBookDto.getIsbn())
            .title(newBookDto.getTitle())
            .author(newBookDto.getAuthor())
            .build();
    }

    private GetAllBooksQuery buildGetAllBooksQuery(String ownerId) {
        return new GetAllBooksQuery(ownerId);
    }
}
