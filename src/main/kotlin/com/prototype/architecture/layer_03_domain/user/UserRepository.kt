package com.prototype.architecture.layer_03_domain.user

import org.springframework.data.repository.PagingAndSortingRepository

interface UserRepository : PagingAndSortingRepository<UserEntity, UserId>