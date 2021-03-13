package my.home.bc.account.port.out.event;

import broker.BrokerPublisher;
import cqrs.event.EventHandler;
import my.home.bc.account.model.AccountCreated;
import reactor.core.publisher.Mono;

public class OnAccountCreatedPublishInBroker implements EventHandler<AccountCreated> {

    public static final String ROUTING_KEY = "account.created";

    private final BrokerPublisher publisher;

    public OnAccountCreatedPublishInBroker(BrokerPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public Mono<Void> execute(AccountCreated event) {
        var message = PublicAccountCreated.from(event);
        return publisher.publish(ROUTING_KEY, message);
    }

    @Override
    public Class<AccountCreated> eventType() {
        return AccountCreated.class;
    }
}
