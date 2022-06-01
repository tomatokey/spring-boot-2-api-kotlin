package com.prototype.architecture.layer_03_domain.auth

import com.prototype.architecture.layer_03_domain.user.UserId
import com.prototype.architecture.layer_03_domain.userrole.UserRoleType
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority

data class AuthUser(
        var userId: UserId,
        var userRoleTypes: List<UserRoleType>
) : Authentication {

    override fun getAuthorities(): Collection<GrantedAuthority?> {
        return userRoleTypes
    }

    override fun getCredentials(): Any {
        return ""
    }

    override fun getDetails(): Any {
        return ""
    }

    override fun getPrincipal(): Any {
        return userId
    }

    override fun isAuthenticated(): Boolean {
        return true
    }

    @Throws(IllegalArgumentException::class)
    override fun setAuthenticated(isAuthenticated: Boolean) {
    }

    override fun getName(): String {
        return ""
    }
}