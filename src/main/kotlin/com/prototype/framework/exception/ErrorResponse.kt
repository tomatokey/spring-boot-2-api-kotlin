package com.prototype.framework.exception

import org.springframework.http.HttpStatus
import java.io.Serializable

data class ErrorResponse(
        val status: Int,
        val message: String,
        val errors: List<ValidError>? = null
) : Serializable {

    constructor(status: HttpStatus, message: String) : this(status.value(), message)
    constructor(status: HttpStatus, message: String, errors: List<ValidError>?) : this(status.value(), message, errors)

}