package com.tomatokey.prototype.application;

import com.tomatokey.prototype.domain.constant.RoleType;
import com.tomatokey.prototype.domain.models.entities.Role;
import com.tomatokey.prototype.domain.models.entities.User;
import com.tomatokey.prototype.domain.models.values.UserName;
import com.tomatokey.prototype.infrastructure.db.RoleRepository;
import com.tomatokey.prototype.infrastructure.db.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@AllArgsConstructor
@Service
public class UserUseCase {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Transactional
    public User create(final User user, final RoleType ...roleTypes) {
        final User inputUser = new User(null, new UserName("テスト"));
        final User createdUser = userRepository.save(inputUser);
        for(RoleType roleType: roleTypes) {
            if (Objects.isNull(roleType)) {
                throw new IllegalArgumentException("権限が設定されていません");
            }
            final Role createdRole = roleRepository.save(new Role(null, roleType, createdUser.getUserId()));
        }
        return createdUser;
    }

}
