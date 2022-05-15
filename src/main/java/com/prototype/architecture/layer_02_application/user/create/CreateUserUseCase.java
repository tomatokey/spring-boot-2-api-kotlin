package com.prototype.architecture.layer_02_application.user.create;

import com.prototype.architecture.layer_03_domain.user.UserEntity;
import com.prototype.architecture.layer_03_domain.user.UserRepository;
import com.prototype.architecture.layer_03_domain.userrole.UserRoleEntity;
import com.prototype.architecture.layer_03_domain.userrole.UserRoleRepository;
import com.prototype.architecture.layer_03_domain.userrole.UserRoleType;
import com.prototype.framework.configuration.jdbc.annotation.TransactionalForUpd;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@AllArgsConstructor
@Service
public class CreateUserUseCase implements Function<CreateUserInput, CreateUserOutput> {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    private final static List<UserRoleType> DEFAULT_USER_ROLES = List.of(UserRoleType.VIEWER);

    @TransactionalForUpd
    @Override
    public CreateUserOutput apply(CreateUserInput createUserInput) {
        final UserEntity inputEntity = UserEntity.of(createUserInput.userName());
        final UserEntity createdUser = userRepository.save(inputEntity);

        final Iterable<UserRoleEntity> userRoles = userRoleRepository.findAllByUserId(createdUser.getUserId());
        userRoles.forEach(userRole -> userRoleRepository.delete(userRole.getPk()));

        for (UserRoleType userRoleType: DEFAULT_USER_ROLES) {
            userRoleRepository.save(new UserRoleEntity(createdUser.getUserId(), userRoleType));
        }

        return CreateUserOutput.of(createdUser);
    }

}
