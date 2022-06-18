package com.prototype.architecture.layer_02_application.user.create

import com.prototype.SqlForUpd
import com.prototype.architecture.layer_03_domain.user.UserEntity
import com.prototype.architecture.layer_03_domain.user.UserName
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.jdbc.JdbcTestUtils
import javax.sql.DataSource


@SpringBootTest
class CreateUserUseCaseTest {

    @Autowired
    private lateinit var useCase: CreateUserUseCase
    @Autowired
    private lateinit var dataSource: DataSource

    @SqlForUpd(scripts = [
        "/sql/user_tのレコードを全て削除.sql",
        "/sql/user_tに1件レコードを追加.sql"
    ])
    @Test
    fun `レコード追加のテスト`() {
        val input = CreateUserInput(UserName("test"))
        val (_, userName) = useCase(input)
        val recordCount = JdbcTestUtils.countRowsInTable(JdbcTemplate(dataSource), UserEntity.TABLE_NAME)

        assertEquals("test", userName.value)
        assertEquals(2, recordCount)
    }

}