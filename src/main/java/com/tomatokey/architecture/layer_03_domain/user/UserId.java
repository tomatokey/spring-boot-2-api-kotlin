package com.tomatokey.architecture.layer_03_domain.user;

import com.tomatokey.architecture.layer_03_domain.ValueObject;

public final class UserId extends ValueObject<Integer> {

    public UserId(Integer value) {
        super(value);
    }

}
