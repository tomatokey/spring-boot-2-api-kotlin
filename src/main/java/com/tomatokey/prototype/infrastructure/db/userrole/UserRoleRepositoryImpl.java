package com.tomatokey.prototype.infrastructure.db.userrole;

import com.tomatokey.prototype.domain.models.user.User;
import com.tomatokey.prototype.domain.models.user.UserId;
import com.tomatokey.prototype.domain.models.userrole.UserRole;
import com.tomatokey.prototype.domain.models.userrole.UserRolePk;
import com.tomatokey.prototype.domain.models.userrole.UserRoleRepository;
import com.tomatokey.prototype.domain.models.userrole.UserRoleType;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Map;

@AllArgsConstructor
@Repository
public class UserRoleRepositoryImpl implements UserRoleRepository<UserRole, UserRolePk> {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public UserRole save(UserRole entity) {
        final String sql = "INSERT INTO user_role_t VALUES (:userId, :roleType)";
        final Map<String, Object> paramMap = Map.of(
                "userId", entity.getUserId().getValue(),
                "roleType", entity.getRoleType().name()
        );

        jdbcTemplate.update(sql, new MapSqlParameterSource(paramMap));
        return entity;
    }

    @Override
    public Iterable<UserRole> findAllByUserId(UserId userId) {
        final String sql = "SELECT * FROM user_role_t WHERE user_id = :userId";
        final Map<String, Object> paramMap = Map.of(
                "userId", userId.getValue()
        );
        final RowMapper<UserRole> rowMapper = (rs, rowNum) -> new UserRole(
                new UserId(rs.getInt(User.COLUMN_NAME_USER_ID)),
                UserRoleType.valueOf(rs.getString(UserRole.COLUMN_NAME_USER_ROLE_TYPE))
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
