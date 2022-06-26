package com.prototype.framework.exception

import com.prototype.framework.extension.log
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.lang.Nullable
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.server.ResponseStatusException
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.stream.Collectors

@RestControllerAdvice
class ApiExceptionHandler : ResponseEntityExceptionHandler() {
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
    override fun handleExceptionInternal(
        ex: Exception,
        @Nullable body: Any?,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        log.error(ex.message, ex)
        return body?.let {
            super.handleExceptionInternal(ex, body, headers, status, request)
        } ?: run {
            val errorResponse = ErrorResponse(status, status.name)
            return super.handleExceptionInternal(ex, errorResponse, headers, status, request)
        }
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
    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        val errors = ex.bindingResult.fieldErrors.stream()
            .map { error: FieldError -> ValidError.of(error) }
            .collect(Collectors.toList())
        val errorResponse = ErrorResponse(
            status,
            "パラメータが不正です",
            errors
        )
        return handleExceptionInternal(ex, errorResponse, HttpHeaders.EMPTY, status, request)
    }

    /**
     * [ResponseStatusException]をthrowした場合のハンドリングをします
     *
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(ResponseStatusException::class)
    fun handleResponseStatusException(ex: ResponseStatusException, request: WebRequest): ResponseEntity<Any> {
        return handleExceptionInternal(ex, null, ex.responseHeaders, ex.status, request)
    }

    /**
     * 予期せぬエラーが発生した場合のレスポンスをハンドリングします
     *
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(Exception::class)
    fun handleUnexpectedException(ex: Exception, request: WebRequest): ResponseEntity<Any> {
        return handleExceptionInternal(ex, null, HttpHeaders.EMPTY, HttpStatus.INTERNAL_SERVER_ERROR, request)
    }
}