package com.prototype.architecture.layer_02_application.user.create

import com.prototype.architecture.layer_03_domain.user.UserEntity
import com.prototype.architecture.layer_03_domain.user.UserId
import com.prototype.architecture.layer_03_domain.user.UserName

data class CreateUserOutput(
    val userId: UserId,
    val userName: UserName
) {

    companion object {
        fun of(user: UserEntity): CreateUserOutput {
            return CreateUserOutput(
                user.userId!!,
                user.userName
            )
        }
    }
}