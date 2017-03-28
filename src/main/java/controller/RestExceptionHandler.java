package controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import exceptions.NotFoundException;

/**
 * Created by guojing on 2017/3/21.
 */
@ControllerAdvice(basePackages = {"controller"})
public final class RestExceptionHandler {
    /**
     * Handle exception which raised in application.
     * @param e exception
     * @return ResponseEntity
     */
    @ExceptionHandler(NotFoundException.class)
    public HttpEntity<?> handleNotFound(final NotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
