package infrastructure.broker;

import static org.assertj.core.api.Assertions.assertThat;

import broker.Broker;
import broker.BrokerMessage;
import broker.BrokerPublisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

public class BrokerInMemoryTest {

    private Broker broker;

    @BeforeEach
    void setUp() {
        broker = new BrokerInMemory();
    }

    @Test
    void publish_when_no_receiver() {
        BrokerPublisher publisher = new SimplePublisher(broker);
        publisher.publish("routing.key", new FakeMessage("Hello")).block();
    }

    @Test
    void publish_when_receiver_subscribed() {
        BrokerPublisher publisher = new SimplePublisher(broker);
        var receiver = new FakeReceiver(broker, "routing.key");

        publisher.publish("routing.key", new FakeMessage("Hello")).block();
        assertThat(receiver.lastReceivedMessage).isNotNull();
    }

    @Test
    void receive_on_wildcard() {
        BrokerPublisher publisher = new SimplePublisher(broker);
        var receiver = new FakeReceiver(broker, "routing.key");
        var wildcardReceiver = new WildcardReceiver(broker);

        publisher.publish("routing.key", new FakeMessage("First")).block();
        publisher.publish("other-routing-key", new FakeMessage("Second")).block();
        assertThat(wildcardReceiver.lastReceivedMessage).isNotNull().isEqualTo("Second");
        assertThat(receiver.lastReceivedMessage).isNotNull().isEqualTo("First");
    }

    static class FakeMessage implements BrokerMessage {

        public String message;

        public FakeMessage(String message) {
            this.message = message;
        }

        @Override
        public String toString() {
            return message;
        }
    }

    static class FakeReceiver extends AbstractSimpleReceiver {

        String lastReceivedMessage;

        public FakeReceiver(Broker broker, String routingKey) {
            super(broker, routingKey);
        }

        @Override
        public Mono<Void> receive(BrokerMessage message) {
            lastReceivedMessage = message.toString();
            return Mono.empty();
        }
    }

    static class WildcardReceiver extends AbstractSimpleReceiver {

        String lastReceivedMessage;

        public WildcardReceiver(Broker broker) {
            super(broker, "*");
        }

        @Override
        public Mono<Void> receive(BrokerMessage message) {
            lastReceivedMessage = message.toString();
            return Mono.empty();
        }
    }
}
