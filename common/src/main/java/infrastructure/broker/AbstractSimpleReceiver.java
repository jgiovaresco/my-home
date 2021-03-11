package infrastructure.broker;

import broker.Broker;
import broker.BrokerReceiver;

public abstract class AbstractSimpleReceiver implements BrokerReceiver {

    public AbstractSimpleReceiver(Broker broker, String routingKey) {
        broker.subscribe(routingKey, this);
    }
}
