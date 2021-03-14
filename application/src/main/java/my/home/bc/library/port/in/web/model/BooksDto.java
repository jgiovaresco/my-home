package my.home.bc.library.port.in.web.model;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import my.home.bc.library.model.Book;
import my.home.bc.library.query.Books;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BooksDto {

    @Builder.Default
    private final List<BookDto> rows = new ArrayList<>();

    public static BooksDto from(Books books) {
        return BooksDto.builder().rows(books.getRows().stream().map(BookDto::from).collect(toList())).build();
    }
}
