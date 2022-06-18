package com.prototype.architecture.layer_01_presentation.user

import com.prototype.architecture.layer_01_presentation.Response
import com.prototype.architecture.layer_02_application.user.create.CreateUserOutput
import com.prototype.architecture.layer_02_application.user.find.FindUserOutput
import com.prototype.architecture.layer_03_domain.user.UserId
import com.prototype.architecture.layer_03_domain.user.UserName
import com.prototype.architecture.layer_03_domain.user.role.UserRoleType

data class UserResponse(
        val userId: UserId,
        val userName: UserName,
        val roleTypes: List<UserRoleType> = listOf(UserRoleType.VIEWER)
) : Response {

    companion object {
        fun of(user: CreateUserOutput): UserResponse {
            return UserResponse(user.userId, user.userName)
        }

        fun of(user: FindUserOutput): UserResponse {
            return UserResponse(user.userId, user.userName)
        }
    }
}