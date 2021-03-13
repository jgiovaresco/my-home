package my.home.bc.library.infrastructure.repository;

import java.util.ArrayList;
import java.util.List;
import my.home.bc.library.model.Library;
import my.home.bc.library.repository.LibraryRepository;
import reactor.core.publisher.Mono;

public class LibraryRepositoryInMemory implements LibraryRepository {

    private final List<Library> storage = new ArrayList<>();

    public void reset() {
        storage.clear();
    }

    @Override
    public Mono<Boolean> add(Library library) {
        return Mono.just(storage.add(library));
    }

    @Override
    public Mono<Library> get(String id) {
        var found = storage.stream().filter(library -> library.getId().equals(id)).findFirst();
        return Mono.justOrEmpty(found);
    }

    @Override
    public Mono<Library> getByOwnerId(String ownerId) {
        return Mono.justOrEmpty(storage.stream().filter(library -> library.getOwnerId().equals(ownerId)).findFirst());
    }
}
