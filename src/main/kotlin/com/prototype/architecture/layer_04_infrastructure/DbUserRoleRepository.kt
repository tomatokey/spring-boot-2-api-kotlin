package com.prototype.architecture.layer_04_infrastructure

import com.prototype.architecture.layer_03_domain.user.UserEntity
import com.prototype.architecture.layer_03_domain.user.UserId
import com.prototype.architecture.layer_03_domain.userrole.UserRoleEntity
import com.prototype.architecture.layer_03_domain.userrole.UserRolePk
import com.prototype.architecture.layer_03_domain.userrole.UserRoleRepository
import com.prototype.architecture.layer_03_domain.userrole.UserRoleType
import com.prototype.framework.configuration.jdbc.parameter.JdbcBeanPropertySqlParameterSource
import lombok.AllArgsConstructor
import org.apache.catalina.User
import org.springframework.jdbc.core.DataClassRowMapper
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.core.simple.SimpleJdbcInsert
import org.springframework.stereotype.Repository
import java.sql.ResultSet
import java.time.LocalDateTime
import javax.sql.DataSource

@Repository
class DbUserRoleRepository(
        private val dataSource: DataSource,
        private val jdbcTemplate: NamedParameterJdbcTemplate
) : UserRoleRepository<UserRoleEntity, UserRolePk> {

    override fun save(entity: UserRoleEntity): UserRoleEntity {
        val params = JdbcBeanPropertySqlParameterSource(entity.applyForCreate())
        SimpleJdbcInsert(dataSource).withTableName(UserRoleEntity.TABLE_NAME).execute(params)
        return entity
    }

    override fun findAllByUserId(userId: UserId): List<UserRoleEntity> {
        val sql = """
            SELECT *
            FROM ${UserRoleEntity.TABLE_NAME}
            WHERE ${UserEntity.USER_ID} = :${UserEntity.USER_ID}
        """.trimIndent()
        val params = MapSqlParameterSource().apply {
            addValue(UserEntity.USER_ID, userId.value)
        }
        val rowMapper = DataClassRowMapper(UserRoleEntity::class.java)
        return jdbcTemplate.query(sql, params, rowMapper)
    }

    override fun delete(pk: UserRolePk) {
        val sql = """
            DELETE FROM ${UserRoleEntity.TABLE_NAME}
            WHERE ${UserEntity.USER_ID} = :${UserEntity.USER_ID}
            AND ${UserRoleEntity.USER_ROLE_TYPE} = :${UserRoleEntity.USER_ROLE_TYPE}
        """.trimIndent()
        val params = MapSqlParameterSource().apply {
            addValue(UserEntity.USER_ID, pk.userId.value)
            addValue(UserRoleEntity.USER_ROLE_TYPE, pk.roleType.name)
        }
        jdbcTemplate.update(sql, params)
    }
}