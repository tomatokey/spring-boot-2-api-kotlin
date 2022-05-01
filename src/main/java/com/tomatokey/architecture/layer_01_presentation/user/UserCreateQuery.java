package com.tomatokey.architecture.layer_01_presentation.user;

import com.tomatokey.architecture.layer_01_presentation.Query;
import com.tomatokey.architecture.layer_03_domain.user.UserId;
import com.tomatokey.architecture.layer_03_domain.user.UserName;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserCreateQuery implements Query {

    private UserId userId;
    private UserName userName;

}
