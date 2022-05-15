package com.prototype.architecture.layer_01_presentation.user;

import com.prototype.architecture.layer_01_presentation.Response;
import com.prototype.architecture.layer_02_application.user.create.CreateUserOutput;
import com.prototype.architecture.layer_02_application.user.find.FindUserOutput;
import com.prototype.architecture.layer_03_domain.user.UserEntity;
import com.prototype.architecture.layer_03_domain.user.UserId;
import com.prototype.architecture.layer_03_domain.userrole.UserRoleType;
import com.prototype.architecture.layer_03_domain.user.UserName;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class UserResponse implements Response {

    private UserId userId;
    private UserName userName;
    private List<UserRoleType> roleTypes;

    public static UserResponse of(CreateUserOutput user) {
        return UserResponse.builder()
                .userId(user.userId())
                .userName(user.userName())
                .build();
    }

    public static UserResponse of(FindUserOutput user) {
        return UserResponse.builder()
                .userId(user.userId())
                .userName(user.userName())
                .build();
    }

}
