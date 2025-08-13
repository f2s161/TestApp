package edu.t1.app.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotEnoughFundsException.class)
    protected ResponseEntity<Object> handleNotEnoughFundsException(NotEnoughFundsException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
