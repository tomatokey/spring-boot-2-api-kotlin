package com.prototype.architecture.layer_02_application.user.find

import com.prototype.architecture.layer_03_domain.user.UserRepository
import org.springframework.stereotype.Service

@Service
class FindAllUserUseCase(
        val userRepository: UserRepository
) {

    operator fun invoke(): List<FindUserOutput> {
        return userRepository.findAll().map { FindUserOutput.of(it) }
    }
}