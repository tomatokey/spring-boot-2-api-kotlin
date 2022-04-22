package com.tomatokey.prototype.infrastructure.db;

import com.tomatokey.prototype.domain.models.entities.User;
import com.tomatokey.prototype.domain.models.values.id.UserId;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, UserId> {
}
