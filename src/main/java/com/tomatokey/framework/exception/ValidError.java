package com.tomatokey.framework.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.io.Serializable;

@Getter
@Builder
@AllArgsConstructor
public class ValidError implements Serializable {

    private String field;
    private String message;

    public static ValidError of(FieldError error) {
        return ValidError.builder()
                .field(error.getField())
                .message(error.getDefaultMessage())
                .build();
    }

}
