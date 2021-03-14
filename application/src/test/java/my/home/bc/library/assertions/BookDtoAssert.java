package my.home.bc.library.assertions;

import my.home.bc.library.port.in.web.model.BookDto;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class BookDtoAssert extends AbstractAssert<BookDtoAssert, BookDto> {

    public BookDtoAssert(BookDto book) {
        super(book, BookDtoAssert.class);
    }

    public BookDtoAssert hasIsbn(String isbn) {
        isNotNull();
        Assertions
            .assertThat(actual.getIsbn())
            .overridingErrorMessage("Expected book's isbn to be <%s> but was <%s>", isbn, actual.getIsbn())
            .isEqualTo(isbn);

        return this;
    }

    public BookDtoAssert hasTitle(String title) {
        isNotNull();
        Assertions
            .assertThat(actual.getTitle())
            .overridingErrorMessage("Expected book's title to be <%s> but was <%s>", title, actual.getTitle())
            .isEqualTo(title);
        return this;
    }

    public BookDtoAssert hasAuthor(String author) {
        isNotNull();
        Assertions
            .assertThat(actual.getAuthor())
            .overridingErrorMessage("Expected book's author to be <%s> but was <%s>", author, actual.getAuthor())
            .isEqualTo(author);
        return this;
    }
}
