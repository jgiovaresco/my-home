package my.home.bc.library.port.in.web.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewBookDto {

    private final String isbn;
    private final String title;
    private final String author;
}
