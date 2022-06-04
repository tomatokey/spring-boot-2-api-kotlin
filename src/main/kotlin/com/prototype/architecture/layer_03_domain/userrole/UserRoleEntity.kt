package com.prototype.architecture.layer_03_domain.userrole

import com.prototype.architecture.layer_03_domain.Entity
import com.prototype.architecture.layer_03_domain.user.UserEntity
import com.prototype.architecture.layer_03_domain.user.UserId
import com.prototype.architecture.layer_03_domain.userrole.UserRoleEntity.Companion.TABLE_NAME
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table(TABLE_NAME)
data class UserRoleEntity(
        @Id
        @Column(UserEntity.USER_ID)
        val userId: UserId,
        @Id
        @Column(USER_ROLE_TYPE)
        val roleType: UserRoleType
) : Entity() {

    val pk: UserRolePk
        get() = UserRolePk(userId, roleType)

    companion object {
        const val TABLE_NAME = "user_role_t"
        const val USER_ROLE_TYPE = "role_type"
    }
}