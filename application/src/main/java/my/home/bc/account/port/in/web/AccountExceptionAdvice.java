package my.home.bc.account.port.in.web;

import my.home.bc.account.error.AccountNotFoundException;
import my.home.bc.account.error.ExistingAccountException;
import my.home.infrastructure.web.exception.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AccountExceptionAdvice {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handle(ExistingAccountException exception) {
        var status = HttpStatus.CONFLICT;
        ErrorResponse response = new ErrorResponse(status.value(), exception.getMessage());

        return ResponseEntity.status(status).body(response);
    }
}
