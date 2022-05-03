package com.tomatokey.framework.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ErrorResponse implements Serializable {

    private int status;
    private String message;
    private List<ValidError> errors;

    public ErrorResponse(HttpStatus status, String message) {
        this.status = status.value();
        this.message = message;
    }

    public ErrorResponse(HttpStatus status, String message, List<ValidError> errors) {
        this.status = status.value();
        this.message = message;
        this.errors = errors;
    }

}
