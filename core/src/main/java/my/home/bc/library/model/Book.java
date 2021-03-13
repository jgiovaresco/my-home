package my.home.bc.library.model;

import lombok.*;

@AllArgsConstructor
@Builder
@Data
public class Book {

    private final String isbn;
    private final String title;
    private final String author;

    public static Book from(NewBookAdded event) {
        return new Book(event.getIsbn(), event.getTitle(), event.getAuthor());
    }
}
