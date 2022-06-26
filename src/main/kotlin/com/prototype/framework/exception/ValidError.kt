package com.prototype.framework.exception

import org.springframework.validation.FieldError
import java.io.Serializable

data class ValidError(
    val field: String,
    val message: String?
) : Serializable {

    companion object {
        fun of(error: FieldError): ValidError {
            return ValidError(error.field, error.defaultMessage)
        }
    }

}