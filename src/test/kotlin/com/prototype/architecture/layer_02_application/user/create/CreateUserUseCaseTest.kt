package com.prototype.architecture.layer_02_application.user.create

import com.prototype.AbstractTest
import com.prototype.SqlForUpd
import com.prototype.Sqls
import com.prototype.architecture.layer_03_domain.user.UserEntity
import com.prototype.architecture.layer_03_domain.user.UserName
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.jdbc.JdbcTestUtils

class CreateUserUseCaseTest : AbstractTest() {

    @Autowired
    private lateinit var useCase: CreateUserUseCase

    @SqlForUpd(
        scripts = [
            Sqls.user_tのレコードを全て削除,
            Sqls.user_tに1件レコードを追加
        ]
    )
    @Test
    fun `レコード追加のテスト`() {
        val input = CreateUserInput(UserName("test"))
        val (_, userName) = useCase(input)
        val recordCount = JdbcTestUtils.countRowsInTable(jdbcTemplate, UserEntity.TABLE_NAME)

        assertEquals("test", userName.value)
        assertEquals(2, recordCount)
    }

}