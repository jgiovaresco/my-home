package my.home.bc.library.port.in.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import my.home.bc.library.model.Book;

@Data
@Builder
@AllArgsConstructor
public class BookDto {

    private final String isbn;
    private final String title;
    private final String author;

    public static BookDto from(Book book) {
        return BookDto.builder().isbn(book.getIsbn()).author(book.getAuthor()).title(book.getTitle()).build();
    }
}
