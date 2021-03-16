package my.home.bc.library.model;

import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@ToString
public class Book {

    private final String isbn;
    private final String title;
    private final String author;
}
