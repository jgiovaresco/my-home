package my.home.bc.account.error;

import ddd.BusinessError;

public class AccountNotFoundException extends BusinessError {

    public AccountNotFoundException() {
        super("ACCOUNT_NOT_FOUND");
    }
}
