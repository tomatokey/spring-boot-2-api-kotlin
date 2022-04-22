package com.tomatokey.prototype.infrastructure.db;

import com.tomatokey.prototype.domain.models.entities.Role;
import com.tomatokey.prototype.domain.models.values.id.RoleId;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RoleRepository extends PagingAndSortingRepository<Role, RoleId> {
}
