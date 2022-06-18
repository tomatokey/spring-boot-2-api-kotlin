package com.prototype.architecture.layer_03_domain.user.role

import com.prototype.architecture.layer_03_domain.user.UserId

/**
 * UserRoleは複合主キーであるためJdbcTemplate等で実装する
 * (2022/04/23時点でspring-data-jdbcが複合主キーに対応していないため)
 * @param <T>
 * @param <PK>
</PK></T> */
interface UserRoleRepository<T, PK> {
    fun save(entity: T): T
    fun findAllByUserId(userId: UserId): List<T>
    fun delete(pk: PK)
}