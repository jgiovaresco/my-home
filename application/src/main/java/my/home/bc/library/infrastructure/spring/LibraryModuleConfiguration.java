package my.home.bc.library.infrastructure.spring;

import my.home.bc.library.infrastructure.repository.LibraryRepositoryInMemory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

@Configuration
@Import(
    {
        LibraryCommandsConfiguration.class,
        LibraryQueriesConfiguration.class,
        LibraryEventsConfiguration.class,
        LibraryBrokerModule.class,
    }
)
public class LibraryModuleConfiguration {

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public LibraryRepositoryInMemory libraryRepositoryInMemory() {
        return new LibraryRepositoryInMemory();
    }
}
