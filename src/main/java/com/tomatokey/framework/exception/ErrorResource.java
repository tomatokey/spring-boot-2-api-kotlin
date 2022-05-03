package com.tomatokey.framework.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter
@Builder
@AllArgsConstructor
public class ErrorResource implements Serializable {

    private int status;
    private String message;

    public ErrorResource(HttpStatus status, String message) {
        this.status = status.value();
        this.message = message;
    }

}
