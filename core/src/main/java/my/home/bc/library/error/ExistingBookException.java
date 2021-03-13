package my.home.bc.library.error;

import ddd.BusinessError;

public class ExistingBookException extends BusinessError {

    public ExistingBookException() {
        super("EXISTING_BOOK");
    }
}
