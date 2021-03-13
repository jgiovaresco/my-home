package my.home.bc.account.error;

import ddd.BusinessError;

public class ExistingAccountException extends BusinessError {

    public ExistingAccountException() {
        super("EXISTING_ACCOUNT");
    }
}
