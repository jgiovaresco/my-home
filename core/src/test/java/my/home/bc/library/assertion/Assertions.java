package my.home.bc.library.assertion;

import my.home.bc.library.model.Book;
import my.home.bc.library.model.Library;

public class Assertions {

    public static BookAssert assertThat(Book book) {
        return new BookAssert(book);
    }

    public static LibraryAssert assertThat(Library library) {
        return new LibraryAssert(library);
    }
}
