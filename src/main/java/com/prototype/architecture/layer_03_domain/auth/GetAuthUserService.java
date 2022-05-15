package com.prototype.architecture.layer_03_domain.auth;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.function.Supplier;

@Service
public class GetAuthUserService implements Supplier<AuthUser> {

    @Override
    public AuthUser get() {
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof AuthUser authUser) {
            return authUser;
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }

}
