package my.home.bc.library.assertions;

import my.home.bc.library.port.in.web.model.BookDto;

public class Assertions {

    public static BookDtoAssert assertThat(BookDto bookDto) {
        return new BookDtoAssert(bookDto);
    }
}
