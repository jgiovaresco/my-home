package my.home.bc.library.repository;

import my.home.bc.library.model.Library;
import reactor.core.publisher.Mono;

public interface LibraryRepository {
    Mono<Boolean> add(Library library);

    Mono<Library> getByOwnerId(String ownerId);

    Mono<Library> get(String id);
}
