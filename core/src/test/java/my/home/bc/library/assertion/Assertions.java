package my.home.bc.library.assertion;

import my.home.bc.library.model.Library;

public class Assertions {

    public static LibraryAssert assertThat(Library library) {
        return new LibraryAssert(library);
    }
}
