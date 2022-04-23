package com.tomatokey.prototype.domain.models.user;

import com.tomatokey.prototype.domain.models.user.User;
import com.tomatokey.prototype.domain.models.user.UserId;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, UserId> {
}
