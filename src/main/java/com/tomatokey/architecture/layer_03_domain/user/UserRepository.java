package com.tomatokey.architecture.layer_03_domain.user;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, UserId> {
}
