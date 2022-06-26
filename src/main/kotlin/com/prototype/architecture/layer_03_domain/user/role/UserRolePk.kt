package com.prototype.architecture.layer_03_domain.user.role

import com.prototype.architecture.layer_03_domain.user.UserId

data class UserRolePk(
    val userId: UserId,
    val roleType: UserRoleType
)