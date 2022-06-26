package com.prototype.architecture.layer_02_application.user.create

import com.prototype.architecture.layer_03_domain.user.UserEntity
import com.prototype.architecture.layer_03_domain.user.UserRepository
import com.prototype.architecture.layer_03_domain.user.role.UserRoleEntity
import com.prototype.architecture.layer_03_domain.user.role.UserRolePk
import com.prototype.architecture.layer_03_domain.user.role.UserRoleRepository
import com.prototype.architecture.layer_03_domain.user.role.UserRoleType
import com.prototype.framework.configuration.jdbc.annotation.TransactionalForUpd
import org.springframework.stereotype.Service
import java.util.function.Consumer

@Service
class CreateUserUseCase(
    val userRepository: UserRepository,
    val userRoleRepository: UserRoleRepository<UserRoleEntity, UserRolePk>
) {

    @TransactionalForUpd
    operator fun invoke(createUserInput: CreateUserInput): CreateUserOutput {
        val inputEntity = UserEntity.of(createUserInput.userName)
        val createdUser = userRepository.save(inputEntity)
        val userRoles = userRoleRepository.findAllByUserId(createdUser.userId!!)
        userRoles.forEach(Consumer { userRole: UserRoleEntity -> userRoleRepository.delete(userRole.pk) })
        for (userRoleType in DEFAULT_USER_ROLES) {
            userRoleRepository.save(UserRoleEntity(createdUser.userId, userRoleType))
        }
        return CreateUserOutput.of(createdUser)
    }

    companion object {
        private val DEFAULT_USER_ROLES = listOf(UserRoleType.VIEWER)
    }
}