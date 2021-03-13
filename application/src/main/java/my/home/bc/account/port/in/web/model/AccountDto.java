package my.home.bc.account.port.in.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import my.home.bc.account.model.Account;

@Data
@Builder
@AllArgsConstructor
public class AccountDto {

    private String id;
    private String externalId;
    private String email;
    private String firstName;
    private String lastName;

    public static AccountDto from(Account account) {
        return AccountDto
            .builder()
            .id(account.getId())
            .externalId(account.getExternalId())
            .email(account.getEmail())
            .firstName(account.getFirstName())
            .lastName(account.getLastName())
            .build();
    }
}
