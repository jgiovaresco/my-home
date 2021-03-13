package my.home.bc.library.query;

import cqrs.query.QueryHandler;
import my.home.bc.library.error.LibraryNotFoundException;
import my.home.bc.library.model.Library;
import my.home.bc.library.query.projection.BooksProjection;
import my.home.bc.library.repository.LibraryRepository;
import reactor.core.publisher.Mono;

public class GetAllBooksQueryHandler implements QueryHandler<GetAllBooksQuery, Books> {

    private final LibraryRepository libraryRepository;
    private final BooksProjection booksProjection;

    public GetAllBooksQueryHandler(LibraryRepository libraryRepository, BooksProjection booksProjection) {
        this.libraryRepository = libraryRepository;
        this.booksProjection = booksProjection;
    }

    @Override
    public Mono<Books> execute(GetAllBooksQuery query) {
        return getLibrary(query.getOwnerId())
            .flatMapMany(library -> booksProjection.getAllBooks(library.getId()))
            .collectList()
            .map(books -> Books.builder().rows(books).build());
    }

    @Override
    public Class<GetAllBooksQuery> queryType() {
        return GetAllBooksQuery.class;
    }

    private Mono<Library> getLibrary(String ownerId) {
        return libraryRepository.getByOwnerId(ownerId).switchIfEmpty(Mono.error(new LibraryNotFoundException()));
    }
}
