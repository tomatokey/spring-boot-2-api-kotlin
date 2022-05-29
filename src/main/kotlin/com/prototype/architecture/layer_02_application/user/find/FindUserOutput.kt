package com.prototype.architecture.layer_02_application.user.find

import com.prototype.architecture.layer_03_domain.user.UserEntity
import com.prototype.architecture.layer_03_domain.user.UserId
import com.prototype.architecture.layer_03_domain.user.UserName

data class FindUserOutput(
        var userId: UserId,
        var userName: UserName
) {

    companion object {
        fun of(user: UserEntity): FindUserOutput {
            return FindUserOutput(
                    user.userId!!,
                    user.userName
            )
        }
    }
}