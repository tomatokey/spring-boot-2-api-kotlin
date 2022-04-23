package com.tomatokey.prototype.domain.models.userrole;

import com.tomatokey.prototype.domain.models.user.UserId;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class UserRolePk {

    private UserId userId;

    private UserRoleType roleType;

}
