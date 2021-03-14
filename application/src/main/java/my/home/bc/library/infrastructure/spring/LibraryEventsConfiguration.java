package my.home.bc.library.infrastructure.spring;

import my.home.bc.library.query.projection.BooksProjection;
import my.home.bc.library.query.projection.OnNewBookAddedFillBooksProjection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LibraryEventsConfiguration {

    @Bean
    public OnNewBookAddedFillBooksProjection onNewBookAddedFillBooksProjection(BooksProjection projection) {
        return new OnNewBookAddedFillBooksProjection(projection);
    }
}
