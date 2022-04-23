package com.tomatokey.prototype.domain.userrole;

import com.tomatokey.prototype.domain.user.UserId;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class UserRolePk {

    private UserId userId;

    private UserRoleType roleType;

}
