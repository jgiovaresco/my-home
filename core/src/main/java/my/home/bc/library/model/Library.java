package my.home.bc.library.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.*;
import my.home.bc.library.error.ExistingBookException;

@AllArgsConstructor
@Builder
@Getter
@Setter(AccessLevel.PRIVATE)
public class Library {

    private String id;
    private String accountId;
    private String ownerId;

    @Builder.Default
    private Map<String, Book> books = new HashMap<>();

    public void addBook(Book book) {
        if (books.containsKey(book.getIsbn())) {
            throw new ExistingBookException();
        }

        books.put(book.getIsbn(), book);
    }

    public Optional<Book> getBook(String isbn) {
        return Optional.ofNullable(books.get(isbn));
    }
}
