package com.tomatokey.framework.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Object> handleUnexpectedException(Throwable e, WebRequest request) {
        log.error(e.getMessage(), e);
        final ErrorResource errorResource = new ErrorResource(HttpStatus.INTERNAL_SERVER_ERROR, "予期せぬ例外が起こりました。");
        return new ResponseEntity<>(errorResource, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
