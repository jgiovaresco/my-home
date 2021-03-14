package my.home.bc.library.port.in.web;

import my.home.bc.library.error.ExistingBookException;
import my.home.bc.library.error.LibraryNotFoundException;
import my.home.infrastructure.web.exception.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class LibraryExceptionAdvice {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handle(LibraryNotFoundException exception) {
        var status = HttpStatus.NOT_FOUND;
        ErrorResponse response = new ErrorResponse(status.value(), exception.getMessage());

        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handle(ExistingBookException exception) {
        var status = HttpStatus.CONFLICT;
        ErrorResponse response = new ErrorResponse(status.value(), exception.getMessage());

        return ResponseEntity.status(status).body(response);
    }
}
