package my.home.bc.account.assertion;

import my.home.bc.account.port.in.web.model.AccountDto;

public class Assertions {

    public static AccountDtoAssert assertThat(AccountDto account) {
        return new AccountDtoAssert(account);
    }
}
