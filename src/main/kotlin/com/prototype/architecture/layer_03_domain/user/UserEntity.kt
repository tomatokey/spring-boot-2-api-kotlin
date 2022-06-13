package com.prototype.architecture.layer_03_domain.user

import com.prototype.architecture.layer_03_domain.Entity
import com.prototype.architecture.layer_03_domain.user.UserEntity.Companion.TABLE_NAME
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table(TABLE_NAME)
data class UserEntity(
        @Id
        @Column(USER_ID)
        val userId: UserId? = null,
        @Column(USER_NAME)
        val userName: UserName
) : Entity() {

    companion object {
        const val TABLE_NAME = "user_t"
        const val USER_ID = "user_id"
        const val USER_NAME = "user_name"
        fun of(userName: UserName): UserEntity {
            return UserEntity(userName = userName)
        }
    }
}