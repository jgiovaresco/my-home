package my.home.bc.library.error;

import ddd.BusinessError;

public class LibraryNotFoundException extends BusinessError {

    public LibraryNotFoundException() {
        super("LIBRARY_NOT_FOUND");
    }
}
