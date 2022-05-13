package com.prototype.architecture.layer_01_presentation.user;

import com.prototype.architecture.layer_01_presentation.Response;
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

    public static UserResponse of(UserEntity user) {
        return UserResponse.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .build();
    }

}
