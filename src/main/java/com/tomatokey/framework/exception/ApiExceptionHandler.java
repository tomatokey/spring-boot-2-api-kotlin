package com.tomatokey.framework.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * リクエストパラメータのバリデーションでエラーが発生した場合のレスポンスをハンドリングします
     *
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error(ex.getMessage(), ex);
        final List<ValidError> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(ValidError::of)
                .collect(Collectors.toList());
        final ErrorResponse errorResponse = new ErrorResponse(
                status,
                "パラメータが不正です",
                errors
        );
        return new ResponseEntity<>(errorResponse, new HttpHeaders(), status);
    }

    /**
     * 予期せぬエラーが発生した場合のレスポンスをハンドリングします
     *
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorResponse> handleUnexpectedException(Throwable ex, WebRequest request) {
        log.error(ex.getMessage(), ex);
        final ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "予期せぬ例外が起こりました。");
        return new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
