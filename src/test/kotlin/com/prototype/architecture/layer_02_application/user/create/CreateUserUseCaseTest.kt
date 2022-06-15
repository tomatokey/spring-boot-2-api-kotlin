package com.prototype.architecture.layer_02_application.user.create

import com.prototype.architecture.layer_03_domain.user.UserEntity
import com.prototype.architecture.layer_03_domain.user.UserName
import com.prototype.architecture.layer_03_domain.userrole.UserRoleEntity
import com.prototype.framework.configuration.jdbc.annotation.TransactionalForUpd
import com.prototype.framework.configuration.jdbc.datasource.DataSourceType
import com.prototype.framework.configuration.jdbc.datasource.MultiDataSourceContextHolder
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.jdbc.JdbcTestUtils
import org.springframework.transaction.annotation.Transactional
import javax.sql.DataSource


@SpringBootTest
class CreateUserUseCaseTest {

    @Autowired
    private lateinit var useCase: CreateUserUseCase
    @Autowired
    @Qualifier("dataSourceUpd")
    private lateinit var dataSource: DataSource
    
    @BeforeEach
    fun beforeEach() {
        JdbcTestUtils.deleteFromTables(JdbcTemplate(dataSource), UserEntity.TABLE_NAME, UserRoleEntity.TABLE_NAME)
    }

    @Test
    fun apply1() {
        val input = CreateUserInput(UserName("test"))
        val (_, userName) = useCase(input)
        val recordCount = JdbcTestUtils.countRowsInTable(JdbcTemplate(dataSource), UserEntity.TABLE_NAME)

        assertEquals("test", userName.value)
        assertEquals(1, recordCount)
    }

    @Test
    fun apply2() {
        val input = CreateUserInput(UserName("test"))
        val (_, userName) = useCase(input)
        val recordCount = JdbcTestUtils.countRowsInTable(JdbcTemplate(dataSource), UserEntity.TABLE_NAME)

        assertEquals("test", userName.value)
        assertEquals(1, recordCount)
    }

}