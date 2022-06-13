package com.prototype.architecture.layer_02_application.user.create

import com.prototype.architecture.layer_03_domain.user.UserEntity
import com.prototype.architecture.layer_03_domain.user.UserName
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.jdbc.JdbcTestUtils

@SpringBootTest
internal class CreateUserUseCaseTest {

    @Autowired
    private lateinit var useCase: CreateUserUseCase
    @Autowired
    private lateinit var jdbcTemplate: JdbcTemplate

    @Test
    fun apply() {
        val input = CreateUserInput(UserName("test"))
        val (_, userName) = useCase(input)
        val recordCount = JdbcTestUtils.countRowsInTable(jdbcTemplate, UserEntity.TABLE_NAME)

        assertEquals("test", userName.value)
        assertEquals(1, recordCount)
    }
}