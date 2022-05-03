package com.tomatokey.framework.exception;

import com.tomatokey.framework.utils.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
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
     * 共通のエラー処理
     * このクラス内でハンドリングしたエラーは全てこのメソッドを経由してください
     *
     * @param ex
     * @param body
     * @param headers
     * @param status
     * @param request
     * @return
     */
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error(ex.getMessage(), ex);

        if (ObjectUtils.isEmpty(body)) {
            final ErrorResponse errorResponse = new ErrorResponse(status, status.name());
            return super.handleExceptionInternal(ex, errorResponse, headers, status, request);
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

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
        final List<ValidError> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(ValidError::of)
                .collect(Collectors.toList());
        final ErrorResponse errorResponse = new ErrorResponse(
                status,
                "パラメータが不正です",
                errors
        );
        return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), status, request);
    }

    /**
     * 予期せぬエラーが発生した場合のレスポンスをハンドリングします
     *
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUnexpectedException(Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

}
