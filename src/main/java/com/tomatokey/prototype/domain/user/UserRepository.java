package com.tomatokey.prototype.domain.user;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, UserId> {
}
