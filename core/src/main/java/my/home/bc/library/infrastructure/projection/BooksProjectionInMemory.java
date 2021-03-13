package my.home.bc.library.infrastructure.projection;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import my.home.bc.library.model.Book;
import my.home.bc.library.model.NewBookAdded;
import my.home.bc.library.query.projection.BooksProjection;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class BooksProjectionInMemory implements BooksProjection {

    private final List<BookItem> books = new ArrayList<>();

    @Override
    public Mono<Void> update(NewBookAdded newBook) {
        books.add(toBookItem(newBook));
        return Mono.empty();
    }

    @Override
    public Flux<Book> getAllBooks(String libraryId) {
        return Flux.fromStream(books.stream().filter(b -> b.libraryId.equals(libraryId)).map(BookItem::getBook));
    }

    private static BookItem toBookItem(NewBookAdded added) {
        return new BookItem(added.getLibraryId(), Book.from(added));
    }

    @AllArgsConstructor
    @Getter
    private static class BookItem {

        private final String libraryId;
        private final Book book;
    }
}
