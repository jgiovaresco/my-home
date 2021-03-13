package my.home.infrastructure.spring;

import infrastructure.ddd.UuidGenerator;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class PersistenceModule {

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public UuidGenerator uuidGenerator() {
        return new UuidGenerator();
    }
}
