package com.prototype.architecture.layer_03_domain.user

import com.prototype.architecture.layer_03_domain.Entity
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("user_t")
data class UserEntity(
        @Id
        @Column(USER_ID)
        val userId: UserId? = null,
        @Column(COLUMN_NAME_USER_NAME)
        val userName: UserName
) : Entity() {

    companion object {
        const val USER_ID = "user_id"
        const val COLUMN_NAME_USER_NAME = "user_name"
        fun of(userName: UserName): UserEntity {
            return UserEntity(userName = userName)
        }
    }
}