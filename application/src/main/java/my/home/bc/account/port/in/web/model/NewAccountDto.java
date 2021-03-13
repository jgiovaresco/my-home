package my.home.bc.account.port.in.web.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewAccountDto {

    private String firstName;
    private String lastName;
}
