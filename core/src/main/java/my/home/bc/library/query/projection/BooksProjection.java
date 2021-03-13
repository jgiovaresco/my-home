package my.home.bc.library.query.projection;

import my.home.bc.library.model.Book;
import my.home.bc.library.model.NewBookAdded;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BooksProjection {
    Mono<Void> update(NewBookAdded newBook);

    Flux<Book> getAllBooks(String libraryId);
}
