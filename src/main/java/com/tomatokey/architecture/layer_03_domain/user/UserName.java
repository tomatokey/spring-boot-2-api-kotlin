package com.tomatokey.architecture.layer_03_domain.user;

import com.tomatokey.architecture.layer_03_domain.ValueObject;

public final class UserName extends ValueObject<String> {

    public UserName(String value) {
        super(value);
    }

}
