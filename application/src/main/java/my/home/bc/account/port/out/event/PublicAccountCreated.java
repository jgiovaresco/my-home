package my.home.bc.account.port.out.event;

import broker.BrokerMessage;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import my.home.bc.account.model.AccountCreated;

@Data
@Builder(access = AccessLevel.PRIVATE)
public class PublicAccountCreated implements BrokerMessage {

    private final String accountId;
    private final String externalId;
    private final String email;

    public static PublicAccountCreated from(AccountCreated event) {
        return PublicAccountCreated
            .builder()
            .accountId(event.getId())
            .externalId(event.getExternalId())
            .email(event.getEmail())
            .build();
    }
}
