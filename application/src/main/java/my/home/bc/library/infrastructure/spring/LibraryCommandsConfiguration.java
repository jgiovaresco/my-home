package my.home.bc.library.infrastructure.spring;

import ddd.IdGenerator;
import my.home.bc.library.command.AddBookCommandHandler;
import my.home.bc.library.command.CreateLibraryCommandHandler;
import my.home.bc.library.repository.LibraryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LibraryCommandsConfiguration {

    @Bean
    public CreateLibraryCommandHandler createLibraryCommandHandler(
        IdGenerator generator,
        LibraryRepository repository
    ) {
        return new CreateLibraryCommandHandler(generator, repository);
    }

    @Bean
    public AddBookCommandHandler addBookCommandHandler(LibraryRepository libraryRepository) {
        return new AddBookCommandHandler(libraryRepository);
    }
}
