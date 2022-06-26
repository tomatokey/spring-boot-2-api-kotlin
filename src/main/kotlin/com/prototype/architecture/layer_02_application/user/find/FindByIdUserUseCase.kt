package com.prototype.architecture.layer_02_application.user.find

import com.prototype.architecture.layer_03_domain.user.UserId
import com.prototype.architecture.layer_03_domain.user.UserRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class FindByIdUserUseCase(
    val userRepository: UserRepository
) {

    operator fun invoke(userId: UserId): Optional<FindUserOutput> {
        return userRepository.findById(userId).map { FindUserOutput.of(it) }
    }
}