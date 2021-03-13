package my.home.bc.account.infrastructure.spring;

import ddd.IdGenerator;
import my.home.bc.account.command.CreateAccountCommandHandler;
import my.home.bc.account.repository.AccountRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountCommandsConfiguration {

    @Bean
    public CreateAccountCommandHandler registerUserCommandHandler(
        IdGenerator generator,
        AccountRepository accountRepository
    ) {
        return new CreateAccountCommandHandler(generator, accountRepository);
    }
}
