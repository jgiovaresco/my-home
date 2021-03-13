package my.home.bc.library.assertion;

import my.home.bc.library.model.Book;
import my.home.bc.library.model.Library;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class LibraryAssert extends AbstractAssert<LibraryAssert, Library> {

    public LibraryAssert(Library account) {
        super(account, LibraryAssert.class);
    }

    public LibraryAssert hasId(String id) {
        isNotNull();
        Assertions
            .assertThat(actual.getId())
            .overridingErrorMessage("Expected library's id to be <%s> but was <%s>", id, actual.getId())
            .isEqualTo(id);

        return this;
    }

    public LibraryAssert hasAccountId(String accountId) {
        isNotNull();
        Assertions
            .assertThat(actual.getAccountId())
            .overridingErrorMessage(
                "Expected library's accountId to be <%s> but was <%s>",
                accountId,
                actual.getAccountId()
            )
            .isEqualTo(accountId);
        return this;
    }

    public LibraryAssert hasOwnerId(String ownerId) {
        isNotNull();
        Assertions
            .assertThat(actual.getOwnerId())
            .overridingErrorMessage("Expected library's ownerId to be <%s> but was <%s>", ownerId, actual.getOwnerId())
            .isEqualTo(ownerId);
        return this;
    }

    public LibraryAssert containsBook(Book expected) {
        Assertions
            .assertThat(actual.getBook(expected.getIsbn()))
            .overridingErrorMessage("Expected library does not contains the book <%s>", expected)
            .isPresent()
            .get()
            .satisfies(
                book ->
                    new BookAssert(book).hasTitle(book.getTitle()).hasAuthor(book.getAuthor()).hasIsbn(book.getIsbn())
            );
        return this;
    }
}
