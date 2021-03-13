package my.home.bc.library.query;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import my.home.bc.library.model.Book;

@Builder
@AllArgsConstructor
@Getter
public class Books {

    private final List<Book> rows;
}
