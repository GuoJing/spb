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
public class RestExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public HttpEntity<?> handleNotFound(NotFoundException e) {
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
