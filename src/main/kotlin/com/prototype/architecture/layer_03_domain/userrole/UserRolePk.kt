package com.prototype.architecture.layer_03_domain.userrole

import com.prototype.architecture.layer_03_domain.user.UserId
import lombok.AllArgsConstructor
import lombok.Getter

data class UserRolePk(
        val userId: UserId,
        val roleType: UserRoleType
)