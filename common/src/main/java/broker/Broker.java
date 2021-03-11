package broker;

import reactor.core.publisher.Mono;

public interface Broker {
    Mono<Void> publish(String routingKey, BrokerMessage message);

    void subscribe(String routingKey, BrokerReceiver receiver);
}
