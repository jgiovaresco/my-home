package my.home.infrastructure.spring;

import broker.Broker;
import infrastructure.broker.BrokerInMemory;
import infrastructure.broker.SimplePublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BrokerModule {

    @Bean
    public Broker broker() {
        return new BrokerInMemory();
    }

    @Bean
    public SimplePublisher simplePublisher(Broker broker) {
        return new SimplePublisher(broker);
    }
}
