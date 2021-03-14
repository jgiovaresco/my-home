package my.home.bc.library.infrastructure.spring;

import my.home.bc.library.infrastructure.projection.BooksProjectionInMemory;
import my.home.bc.library.query.GetAllBooksQueryHandler;
import my.home.bc.library.query.projection.BooksProjection;
import my.home.bc.library.repository.LibraryRepository;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class LibraryQueriesConfiguration {

    @Bean
    public GetAllBooksQueryHandler getAllBooksQueryHandler(
        LibraryRepository libraryRepository,
        BooksProjection projection
    ) {
        return new GetAllBooksQueryHandler(libraryRepository, projection);
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public BooksProjectionInMemory booksProjectionInMemory() {
        return new BooksProjectionInMemory();
    }
}
