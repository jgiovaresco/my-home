package my.home.bc.library.command;

import cqrs.command.CommandHandler;
import cqrs.command.CommandResponse;
import my.home.bc.library.error.LibraryNotFoundException;
import my.home.bc.library.model.Book;
import my.home.bc.library.model.Library;
import my.home.bc.library.model.NewBookAdded;
import my.home.bc.library.repository.LibraryRepository;
import reactor.core.publisher.Mono;

public class AddBookCommandHandler implements CommandHandler<AddBookCommand, Book> {

    private final LibraryRepository libraryRepository;

    public AddBookCommandHandler(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    @Override
    public Mono<CommandResponse<Book>> execute(AddBookCommand command) {
        Book newBook = new Book(command.getIsbn(), command.getTitle(), command.getAuthor());

        return getLibrary(command.getOwnerId())
            .map(
                library -> {
                    NewBookAdded newBookAdded = new NewBookAdded(
                        library.getId(),
                        command.getIsbn(),
                        command.getTitle(),
                        command.getAuthor()
                    );
                    library.addBook(newBook);
                    return new CommandResponse<>(newBook, newBookAdded);
                }
            );
    }

    @Override
    public Class<AddBookCommand> commandType() {
        return AddBookCommand.class;
    }

    private Mono<Library> getLibrary(String ownerId) {
        return libraryRepository.getByOwnerId(ownerId).switchIfEmpty(Mono.error(new LibraryNotFoundException()));
    }
}
