package com.prototype.architecture.layer_02_application.user.find;

import com.prototype.architecture.layer_03_domain.user.UserEntity;
import com.prototype.architecture.layer_03_domain.user.UserId;
import com.prototype.architecture.layer_03_domain.user.UserName;

public record FindUserOutput(
        UserId userId,
        UserName userName
) {

    public static FindUserOutput of(UserEntity user) {
        return new FindUserOutput(
                user.getUserId(),
                user.getUserName()
        );
    }

}
