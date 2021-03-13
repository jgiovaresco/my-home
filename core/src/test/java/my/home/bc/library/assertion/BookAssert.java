package my.home.bc.library.assertion;

import my.home.bc.library.model.Book;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class BookAssert extends AbstractAssert<BookAssert, Book> {

    public BookAssert(Book book) {
        super(book, BookAssert.class);
    }

    public BookAssert hasIsbn(String isbn) {
        isNotNull();
        Assertions
            .assertThat(actual.getIsbn())
            .overridingErrorMessage("Expected book's isbn to be <%s> but was <%s>", isbn, actual.getIsbn())
            .isEqualTo(isbn);

        return this;
    }

    public BookAssert hasTitle(String title) {
        isNotNull();
        Assertions
            .assertThat(actual.getTitle())
            .overridingErrorMessage("Expected book's title to be <%s> but was <%s>", title, actual.getTitle())
            .isEqualTo(title);
        return this;
    }

    public BookAssert hasAuthor(String author) {
        isNotNull();
        Assertions
            .assertThat(actual.getAuthor())
            .overridingErrorMessage("Expected book's author to be <%s> but was <%s>", author, actual.getAuthor())
            .isEqualTo(author);
        return this;
    }
}
