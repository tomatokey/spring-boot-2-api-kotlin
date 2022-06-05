package com.prototype.architecture.layer_01_presentation.user

import com.prototype.architecture.layer_02_application.user.create.CreateUserInput
import com.prototype.architecture.layer_02_application.user.create.CreateUserUseCase
import com.prototype.architecture.layer_02_application.user.find.FindAllUserUseCase
import com.prototype.architecture.layer_02_application.user.find.FindByIdUserUseCase
import com.prototype.architecture.layer_03_domain.auth.GetAuthUserService
import com.prototype.architecture.layer_03_domain.user.UserId
import com.prototype.architecture.layer_03_domain.userrole.UserRoleType
import com.prototype.framework.configuration.mvc.auth.Authorize
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("users")
class UserController(
        private val createUserUseCase: CreateUserUseCase,
        private val findAllUserUseCase: FindAllUserUseCase,
        private val findByIdUserUseCase: FindByIdUserUseCase,
        private val authService: GetAuthUserService
) {

    @PostMapping
    fun create(@RequestBody @Validated query: UserCreateQuery): UserResponse {
        val input = CreateUserInput(query.userName)
        val output = createUserUseCase(input)
        return UserResponse.of(output)
    }

    @Authorize(UserRoleType.ADMIN)
    @GetMapping
    fun findAll(): List<UserResponse> {
        return findAllUserUseCase().map { UserResponse.of(it) }
    }

    @Authorize
    @GetMapping("findByToken")
    fun findByToken(): ResponseEntity<UserResponse> {
        val authUser = authService()
        return findByIdUserUseCase(authUser.userId)
                .map { UserResponse.of(it) }
                .let { ResponseEntity.of(it) }
    }

    @GetMapping("{userId}")
    fun findByPathId(@PathVariable("userId") userId: UserId): ResponseEntity<UserResponse> {
        return findByIdUserUseCase(userId)
                .map { UserResponse.of(it) }
                .let { ResponseEntity.of(it) }
    }

    @GetMapping("findById")
    fun findById(@RequestParam("userId") userId: UserId): ResponseEntity<UserResponse> {
        return findByIdUserUseCase(userId)
                .map { UserResponse.of(it) }
                .let { ResponseEntity.of(it) }
    }
}