package my.home.bc.account.infrastructure.spring;

import broker.BrokerPublisher;
import my.home.bc.account.port.out.event.OnAccountCreatedPublishInBroker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountEventsConfiguration {

    @Bean
    public OnAccountCreatedPublishInBroker onAccountCreatedPublishInBroker(BrokerPublisher publisher) {
        return new OnAccountCreatedPublishInBroker(publisher);
    }
}
