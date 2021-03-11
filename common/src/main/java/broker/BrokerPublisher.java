package broker;

import reactor.core.publisher.Mono;

public interface BrokerPublisher {
    Mono<Void> publish(String routingKey, BrokerMessage message);
}
