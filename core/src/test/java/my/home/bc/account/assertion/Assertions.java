package my.home.bc.account.assertion;

import my.home.bc.account.model.Account;

public class Assertions {

    public static AccountAssert assertThat(Account account) {
        return new AccountAssert(account);
    }
}
