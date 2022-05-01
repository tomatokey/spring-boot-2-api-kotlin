package com.tomatokey.architecture.layer_01_presentation.user;

import com.tomatokey.architecture.layer_01_presentation.Resource;
import com.tomatokey.architecture.layer_03_domain.user.UserId;
import com.tomatokey.architecture.layer_03_domain.user.UserName;
import com.tomatokey.architecture.layer_03_domain.userrole.UserRoleType;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class UserResource implements Resource {

    private UserId userId;
    private UserName userName;
    private List<UserRoleType> roleTypes;

}
