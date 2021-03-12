package my.home.bc.library.command;

import cqrs.command.CommandHandler;
import cqrs.command.CommandResponse;
import ddd.IdGenerator;
import my.home.bc.library.model.Library;
import my.home.bc.library.model.LibraryCreated;
import my.home.bc.library.repository.LibraryRepository;
import reactor.core.publisher.Mono;

public class CreateLibraryCommandHandler implements CommandHandler<CreateLibraryCommand, Library> {

    private final IdGenerator generator;
    private final LibraryRepository libraryRepository;

    public CreateLibraryCommandHandler(IdGenerator generator, LibraryRepository libraryRepository) {
        this.generator = generator;
        this.libraryRepository = libraryRepository;
    }

    @Override
    public Mono<CommandResponse<Library>> execute(CreateLibraryCommand command) {
        var library = Library
            .builder()
            .id(generator.generateId())
            .accountId(command.getAccountId())
            .ownerId(command.getOwnerId())
            .build();
        var libraryCreated = new LibraryCreated(library.getId(), library.getAccountId(), library.getOwnerId());

        return libraryRepository.add(library).map(unused -> new CommandResponse<>(library, libraryCreated));
    }

    @Override
    public Class<CreateLibraryCommand> commandType() {
        return CreateLibraryCommand.class;
    }
}
