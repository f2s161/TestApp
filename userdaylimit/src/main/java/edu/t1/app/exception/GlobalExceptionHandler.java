package edu.t1.app.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserDayLimitCreateException.class)
    protected ResponseEntity<Object> handleUserCreateException(UserDayLimitCreateException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    @ExceptionHandler(UserNotFoundException.class)
    protected ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(UserDayLimitUpdateException.class)
    protected ResponseEntity<Object> handleUserDayLimitUpdateException(UserDayLimitUpdateException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

}
