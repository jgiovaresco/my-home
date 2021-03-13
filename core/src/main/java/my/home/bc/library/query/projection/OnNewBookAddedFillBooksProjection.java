package my.home.bc.library.query.projection;

import cqrs.event.EventHandler;
import my.home.bc.library.model.NewBookAdded;
import reactor.core.publisher.Mono;

public class OnNewBookAddedFillBooksProjection implements EventHandler<NewBookAdded> {

    private final BooksProjection booksProjection;

    public OnNewBookAddedFillBooksProjection(BooksProjection booksProjection) {
        this.booksProjection = booksProjection;
    }

    @Override
    public Mono<Void> execute(NewBookAdded event) {
        return booksProjection.update(event);
    }

    @Override
    public Class<NewBookAdded> eventType() {
        return NewBookAdded.class;
    }
}
