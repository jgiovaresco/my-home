package my.home.bc.account.assertion;

import my.home.bc.account.port.in.web.model.AccountDto;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class AccountDtoAssert extends AbstractAssert<AccountDtoAssert, AccountDto> {

    public AccountDtoAssert(AccountDto account) {
        super(account, AccountDtoAssert.class);
    }

    public AccountDtoAssert hasId(String id) {
        isNotNull();
        Assertions
            .assertThat(actual.getId())
            .overridingErrorMessage("Expected account's id to be <%s> but was <%s>", id, actual.getId())
            .isEqualTo(id);

        return this;
    }

    public AccountDtoAssert hasExternalId(String id) {
        isNotNull();
        Assertions
            .assertThat(actual.getExternalId())
            .overridingErrorMessage(
                "Expected account's external id to be <%s> but was <%s>",
                id,
                actual.getExternalId()
            )
            .isEqualTo(id);

        return this;
    }

    public AccountDtoAssert hasFirstName(String firstName) {
        isNotNull();
        Assertions
            .assertThat(actual.getFirstName())
            .overridingErrorMessage(
                "Expected account's firstName to be <%s> but was <%s>",
                firstName,
                actual.getFirstName()
            )
            .isEqualTo(firstName);
        return this;
    }

    public AccountDtoAssert hasLastName(String lastName) {
        isNotNull();
        Assertions
            .assertThat(actual.getLastName())
            .overridingErrorMessage(
                "Expected account's lastName to be <%s> but was <%s>",
                lastName,
                actual.getLastName()
            )
            .isEqualTo(lastName);
        return this;
    }

    public AccountDtoAssert hasEmail(String email) {
        isNotNull();
        Assertions
            .assertThat(actual.getEmail())
            .overridingErrorMessage("Expected account's email to be <%s> but was <%s>", email, actual.getEmail())
            .isEqualTo(email);
        return this;
    }
}
