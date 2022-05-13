package com.prototype.architecture.layer_03_domain.userrole;

import org.springframework.security.core.GrantedAuthority;

public enum UserRoleType implements GrantedAuthority {
    ADMIN,
    EDITOR,
    AUTHOR,
    VIEWER;

    @Override
    public String getAuthority() {
        return name();
    }
}
