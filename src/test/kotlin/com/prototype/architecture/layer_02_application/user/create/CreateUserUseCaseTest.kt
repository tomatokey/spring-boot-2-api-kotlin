package com.prototype.architecture.layer_02_application.user.create

import com.prototype.architecture.layer_03_domain.user.UserName
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class CreateUserUseCaseTest {

    @Autowired
    private lateinit var useCase: CreateUserUseCase

    @Test
    fun apply() {
        val input = CreateUserInput(UserName("test"))
        val (_, userName) = useCase(input)
        Assertions.assertEquals("test", userName.value)
    }
}