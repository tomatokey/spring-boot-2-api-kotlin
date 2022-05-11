package com.tomatokey.architecture.layer_03_domain.user;

import com.tomatokey.architecture.layer_03_domain.SingleValueObject;

public final class UserId extends SingleValueObject<Integer> {

    public UserId(Integer value) {
        super(value);
    }

}
