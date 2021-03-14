package my.home.bc.library.infrastructure.spring;

import broker.Broker;
import cqrs.command.CommandBus;
import my.home.bc.library.port.in.event.OnPublicAccountCreatedCreateLibrary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LibraryBrokerModule {

    @Bean
    public OnPublicAccountCreatedCreateLibrary onPublicAccountCreatedCreateLibrary(
        Broker broker,
        CommandBus commandBus
    ) {
        return new OnPublicAccountCreatedCreateLibrary(broker, commandBus);
    }
}
