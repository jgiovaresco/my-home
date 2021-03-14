package my.home.bc.library.port.in.event;

import broker.Broker;
import broker.BrokerMessage;
import cqrs.command.CommandBus;
import infrastructure.broker.AbstractSimpleReceiver;
import my.home.bc.account.port.out.event.PublicAccountCreated;
import my.home.bc.library.command.CreateLibraryCommand;
import reactor.core.publisher.Mono;

public class OnPublicAccountCreatedCreateLibrary extends AbstractSimpleReceiver {

    public static final String ROUTING_KEY = "account.created";

    private final CommandBus commandBus;

    public OnPublicAccountCreatedCreateLibrary(Broker broker, CommandBus commandBus) {
        super(broker, ROUTING_KEY);
        this.commandBus = commandBus;
    }

    @Override
    public Mono<Void> receive(BrokerMessage message) {
        PublicAccountCreated event = (PublicAccountCreated) message;

        CreateLibraryCommand command = CreateLibraryCommand
            .builder()
            .accountId(event.getAccountId())
            .ownerId(event.getExternalId())
            .build();

        return commandBus.send(command).then();
    }
}
