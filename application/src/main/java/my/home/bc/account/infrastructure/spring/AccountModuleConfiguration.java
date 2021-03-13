package my.home.bc.account.infrastructure.spring;

import my.home.bc.account.infrastructure.AccountsRepositoryInMemory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

@Configuration
@Import({ AccountCommandsConfiguration.class })
public class AccountModuleConfiguration {

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public AccountsRepositoryInMemory accountsRepositoryInMemory() {
        return new AccountsRepositoryInMemory();
    }
}
