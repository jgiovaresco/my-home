package infrastructure.broker;

import broker.Broker;
import broker.BrokerMessage;
import broker.BrokerPublisher;
import reactor.core.publisher.Mono;

public class SimplePublisher implements BrokerPublisher {

    private final Broker broker;

    public SimplePublisher(Broker broker) {
        this.broker = broker;
    }

    @Override
    public Mono<Void> publish(String routingKey, BrokerMessage message) {
        return broker.publish(routingKey, message);
    }
}
