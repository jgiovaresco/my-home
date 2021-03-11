package infrastructure.broker;

import static java.util.stream.Collectors.toList;

import broker.Broker;
import broker.BrokerMessage;
import broker.BrokerReceiver;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
public class BrokerInMemory implements Broker {

    private static final String WILDCARD = "*";

    private final Map<String, List<BrokerReceiver>> subscribers;

    public BrokerInMemory() {
        this.subscribers = new HashMap<>();
        this.subscribers.put(WILDCARD, new ArrayList<>());
    }

    @Override
    public Mono<Void> publish(String routingKey, BrokerMessage message) {
        log.debug("Publish message {} : {}", routingKey, message);

        return Flux
            .fromStream(
                Stream.concat(
                    subscribers.get(WILDCARD).stream(),
                    subscribers.getOrDefault(routingKey, new ArrayList<>()).stream()
                )
            )
            .flatMap(r -> r.receive(message))
            .then();
    }

    @Override
    public void subscribe(String routingKey, BrokerReceiver receiver) {
        subscribers.putIfAbsent(routingKey, new ArrayList<>());
        subscribers.get(routingKey).add(receiver);
    }
}
