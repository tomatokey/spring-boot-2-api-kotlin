package com.tomatokey.framework.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
@AllArgsConstructor
public class ErrorResource {
    private int status;
    private String message;

    public ErrorResource(HttpStatus status, String message) {
        this.status = status.value();
        this.message = message;
    }

}
