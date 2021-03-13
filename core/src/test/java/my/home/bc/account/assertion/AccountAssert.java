package my.home.bc.account.assertion;

import my.home.bc.account.model.Account;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class AccountAssert extends AbstractAssert<AccountAssert, Account> {

    public AccountAssert(Account account) {
        super(account, AccountAssert.class);
    }

    public AccountAssert hasId(String id) {
        isNotNull();
        Assertions
            .assertThat(actual.getId())
            .overridingErrorMessage("Expected account's id to be <%s> but was <%s>", id, actual.getId())
            .isEqualTo(id);

        return this;
    }

    public AccountAssert hasFirstName(String firstName) {
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

    public AccountAssert hasLastName(String lastName) {
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

    public AccountAssert hasEmail(String email) {
        isNotNull();
        Assertions
            .assertThat(actual.getEmail())
            .overridingErrorMessage("Expected account's email to be <%s> but was <%s>", email, actual.getEmail())
            .isEqualTo(email);
        return this;
    }

    public AccountAssert hasExternalId(String externalId) {
        isNotNull();
        Assertions
            .assertThat(actual.getExternalId())
            .overridingErrorMessage(
                "Expected account's externalId to be <%s> but was <%s>",
                externalId,
                actual.getExternalId()
            )
            .isEqualTo(externalId);
        return this;
    }
}
