package com.tomatokey.prototype.application;

import com.tomatokey.prototype.domain.models.user.User;
import com.tomatokey.prototype.domain.models.user.UserRepository;
import com.tomatokey.prototype.domain.models.userrole.UserRole;
import com.tomatokey.prototype.domain.models.userrole.UserRoleRepository;
import com.tomatokey.prototype.domain.models.userrole.UserRoleType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@AllArgsConstructor
@Service
public class UserUseCase {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    @Transactional
    public User create(final User user, final UserRoleType...roleTypes) {
        final User createdUser = userRepository.save(user);
        final Iterable<UserRole> userRoles = userRoleRepository.findAllByUserId(createdUser.getUserId());
        userRoles.forEach(userRole -> {
            userRoleRepository.delete(userRole.getPk());
        });
        for (UserRoleType roleType: roleTypes) {
            if (Objects.isNull(roleType)) {
                throw new IllegalArgumentException("権限が設定されていません");
            }
            userRoleRepository.save(new UserRole(createdUser.getUserId(), roleType));
        }
        return createdUser;
    }

}
