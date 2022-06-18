package com.prototype.architecture.layer_03_domain.user.role

import org.springframework.security.core.GrantedAuthority

enum class UserRoleType : GrantedAuthority {
    ADMIN, EDITOR, AUTHOR, VIEWER;

    override fun getAuthority(): String {
        return name
    }
}