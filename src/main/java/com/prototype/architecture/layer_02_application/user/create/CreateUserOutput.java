package com.prototype.architecture.layer_02_application.user.create;

import com.prototype.architecture.layer_03_domain.user.UserEntity;
import com.prototype.architecture.layer_03_domain.user.UserId;
import com.prototype.architecture.layer_03_domain.user.UserName;

public record CreateUserOutput(
        UserId userId,
        UserName userName
){

    public static CreateUserOutput of(UserEntity user) {
        return new CreateUserOutput(
                user.getUserId(),
                user.getUserName()
        );
    }

}
