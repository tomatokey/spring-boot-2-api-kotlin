package com.tomatokey.architecture.layer_02_application;

import com.tomatokey.architecture.layer_03_domain.user.User;
import com.tomatokey.architecture.layer_03_domain.user.UserId;
import com.tomatokey.architecture.layer_03_domain.user.UserRepository;
import com.tomatokey.architecture.layer_03_domain.userrole.UserRole;
import com.tomatokey.architecture.layer_03_domain.userrole.UserRoleRepository;
import com.tomatokey.architecture.layer_03_domain.userrole.UserRoleType;
import com.tomatokey.framework.configuration.jdbc.annotation.TransactionalForUpd;
import com.tomatokey.framework.utils.CollectionUtils;
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
    public User create(final User user) {
        final User createdUser = userRepository.save(user);

        final Iterable<UserRole> userRoles = userRoleRepository.findAllByUserId(createdUser.getUserId());
        userRoles.forEach(userRole -> {
            userRoleRepository.delete(userRole.getPk());
        });

        for (UserRoleType userRoleType: DEFAULT_USER_ROLES) {
            userRoleRepository.save(new UserRole(createdUser.getUserId(), userRoleType));
        }

        return createdUser;
    }

    public List<User> findAll() {
        return CollectionUtils.toList(userRepository.findAll());
    }

    public Optional<User> findById(UserId userId) {
        return userRepository.findById(userId);
    }

}
