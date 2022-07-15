package com.prototype.architecture.layer_02_application.user.create

import com.prototype.AbstractTest
import com.prototype.SqlForUpd
import com.prototype.Sqls
import com.prototype.architecture.layer_03_domain.user.UserEntity
import com.prototype.architecture.layer_03_domain.user.UserId
import com.prototype.architecture.layer_03_domain.user.UserName
import com.prototype.architecture.layer_03_domain.user.role.UserRoleEntity
import com.prototype.architecture.layer_03_domain.user.role.UserRolePk
import com.prototype.architecture.layer_03_domain.user.role.UserRoleRepository
import com.prototype.architecture.layer_03_domain.user.role.UserRoleType
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.mockito.kotlin.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.jdbc.JdbcTestUtils

class CreateUserUseCaseTest : AbstractTest() {

    @MockBean
    private lateinit var userRoleRepository: UserRoleRepository<UserRoleEntity, UserRolePk>

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
        // 条件
        val input = CreateUserInput(UserName("test"))

        // 実行
        val (_, userName) = useCase(input)
        val recordCount = JdbcTestUtils.countRowsInTable(jdbcTemplate, UserEntity.TABLE_NAME)

        // 検証
        assertAll(
            { assertEquals("test", userName.value) },
            { assertEquals(2, recordCount) }
        )
    }

    @SqlForUpd(
        scripts = [
            Sqls.user_tのレコードを全て削除,
            Sqls.user_tに1件レコードを追加
        ]
    )
    @Test
    fun `Mockを使ったテスト`() {
        // 条件
        val input = CreateUserInput(UserName("test"))
        val userId = UserId(1)
        val userRoleType = UserRoleType.ADMIN
        doReturn(listOf(UserRoleEntity(userId, userRoleType))).whenever(userRoleRepository).findAllByUserId(any())

        // 実行
        val (_, userName) = useCase(input)
        val recordCount = JdbcTestUtils.countRowsInTable(jdbcTemplate, UserEntity.TABLE_NAME)

        // 検証
        assertAll(
            { assertEquals("test", userName.value) },
            { assertEquals(2, recordCount) },
            { verify(userRoleRepository).delete(UserRolePk(userId, userRoleType)) }
        )
    }

}