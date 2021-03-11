package broker;

import reactor.core.publisher.Mono;

public interface BrokerReceiver {
    Mono<Void> receive(BrokerMessage message);
}
