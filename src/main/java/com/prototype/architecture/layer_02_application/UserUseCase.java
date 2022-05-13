package com.prototype.architecture.layer_02_application;

import com.prototype.architecture.layer_03_domain.user.UserEntity;
import com.prototype.architecture.layer_03_domain.user.UserId;
import com.prototype.architecture.layer_03_domain.user.UserRepository;
import com.prototype.architecture.layer_03_domain.userrole.UserRoleEntity;
import com.prototype.architecture.layer_03_domain.userrole.UserRoleType;
import com.prototype.architecture.layer_03_domain.userrole.UserRoleRepository;
import com.prototype.framework.configuration.jdbc.annotation.TransactionalForUpd;
import com.prototype.framework.utils.CollectionUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserUseCase {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    private final static List<UserRoleType> DEFAULT_USER_ROLES = List.of(UserRoleType.VIEWER);

    @TransactionalForUpd
    public UserEntity create(final UserEntity user) {
        final UserEntity createdUser = userRepository.save(user);

        final Iterable<UserRoleEntity> userRoles = userRoleRepository.findAllByUserId(createdUser.getUserId());
        userRoles.forEach(userRole -> {
            userRoleRepository.delete(userRole.getPk());
        });

        for (UserRoleType userRoleType: DEFAULT_USER_ROLES) {
            userRoleRepository.save(new UserRoleEntity(createdUser.getUserId(), userRoleType));
        }

        return createdUser;
    }

    public List<UserEntity> findAll() {
        return CollectionUtils.toList(userRepository.findAll());
    }

    public Optional<UserEntity> findById(UserId userId) {
        return userRepository.findById(userId);
    }

}
