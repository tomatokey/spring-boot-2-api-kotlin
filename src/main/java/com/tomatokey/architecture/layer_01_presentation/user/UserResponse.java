package com.tomatokey.architecture.layer_01_presentation.user;

import com.tomatokey.architecture.layer_01_presentation.Response;
import com.tomatokey.architecture.layer_03_domain.user.UserEntity;
import com.tomatokey.architecture.layer_03_domain.user.UserId;
import com.tomatokey.architecture.layer_03_domain.user.UserName;
import com.tomatokey.architecture.layer_03_domain.userrole.UserRoleType;
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
