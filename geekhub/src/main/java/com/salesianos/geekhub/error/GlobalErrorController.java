package com.salesianos.geekhub.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
@RestControllerAdvice
public class GlobalErrorController  extends ResponseEntityExceptionHandler {

        @ExceptionHandler(UserNotFoundException.class)
        public ProblemDetail handleUserNotFoundException(UserNotFoundException ex) {
            ProblemDetail result = ProblemDetail
                    .forStatusAndDetail(HttpStatus.NOT_FOUND,
                            ex.getMessage());
            result.setTitle("usuario no encontrado");
            result.setType(URI.create("https://www.salesianos-triana.edu/errors/user-not-found"));

            return result;

        }
}
