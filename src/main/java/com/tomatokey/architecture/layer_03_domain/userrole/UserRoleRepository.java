package com.tomatokey.architecture.layer_03_domain.userrole;

import com.tomatokey.architecture.layer_03_domain.user.UserId;

/**
 * UserRoleは複合主キーであるためJdbcTemplate等で実装する
 * (2022/04/23時点でspring-data-jdbcが複合主キーに対応していないため)
 * @param <T>
 * @param <PK>
 */
public interface UserRoleRepository<T extends UserRole, PK extends UserRolePk> {

    T save(T entity);
    Iterable<T> findAllByUserId(UserId userId);
    void delete(PK pk);

}
