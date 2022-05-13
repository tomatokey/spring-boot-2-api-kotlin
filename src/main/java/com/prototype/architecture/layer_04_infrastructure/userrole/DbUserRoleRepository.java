package com.prototype.architecture.layer_04_infrastructure.userrole;

import com.prototype.architecture.layer_03_domain.user.UserEntity;
import com.prototype.architecture.layer_03_domain.user.UserId;
import com.prototype.architecture.layer_03_domain.userrole.UserRoleEntity;
import com.prototype.architecture.layer_03_domain.userrole.UserRolePk;
import com.prototype.architecture.layer_03_domain.userrole.UserRoleType;
import com.prototype.architecture.layer_03_domain.userrole.UserRoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Map;

@AllArgsConstructor
@Repository
public class DbUserRoleRepository implements UserRoleRepository<UserRoleEntity, UserRolePk> {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public UserRoleEntity save(UserRoleEntity entity) {
        final String sql = "INSERT INTO user_role_t VALUES (:userId, :roleType, :registerTime, :updateTime)";
        final Map<String, Object> paramMap = Map.of(
                "userId", entity.getUserId().getValue(),
                "roleType", entity.getRoleType().name(),
                "registerTime", LocalDateTime.now(),
                "updateTime", LocalDateTime.now()
        );

        jdbcTemplate.update(sql, new MapSqlParameterSource(paramMap));
        return entity;
    }

    @Override
    public Iterable<UserRoleEntity> findAllByUserId(UserId userId) {
        final String sql = "SELECT * FROM user_role_t WHERE user_id = :userId";
        final Map<String, Object> paramMap = Map.of(
                "userId", userId.getValue()
        );
        final RowMapper<UserRoleEntity> rowMapper = (rs, rowNum) -> new UserRoleEntity(
                new UserId(rs.getInt(UserEntity.COLUMN_NAME_USER_ID)),
                UserRoleType.valueOf(rs.getString(UserRoleEntity.COLUMN_NAME_USER_ROLE_TYPE))
        );

        return jdbcTemplate.query(sql, paramMap, rowMapper);
    }

    @Override
    public void delete(UserRolePk pk) {
        final String sql = "DELETE FROM user_role_t WHERE user_id = :userId AND role_type = :roleType";
        final Map<String, Object> paramMap = Map.of(
                "userId", pk.getUserId().getValue(),
                "roleType", pk.getRoleType().name()
        );

        jdbcTemplate.update(sql, paramMap);
    }

}
