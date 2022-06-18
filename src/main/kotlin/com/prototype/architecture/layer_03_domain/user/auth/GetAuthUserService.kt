package com.prototype.architecture.layer_03_domain.user.auth;

import org.springframework.http.HttpStatus
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class GetAuthUserService {

    operator fun invoke(): AuthUser {
        val auth: Authentication = SecurityContextHolder.getContext().authentication
        if (auth is AuthUser) {
            return auth
        }
        throw ResponseStatusException(HttpStatus.FORBIDDEN)
    }

}
