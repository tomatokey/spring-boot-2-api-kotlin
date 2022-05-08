package com.tomatokey.architecture.layer_03_domain.auth;

import com.tomatokey.architecture.layer_03_domain.user.UserId;
import com.tomatokey.architecture.layer_03_domain.userrole.UserRoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@Getter
public final class AuthUser implements Authentication {

    private final UserId userId;
    private final List<UserRoleType> userRoleTypes;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userRoleTypes;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return userId;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return null;
    }
}
