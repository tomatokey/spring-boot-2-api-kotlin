package com.prototype.architecture.layer_03_domain.user;

import com.prototype.architecture.layer_03_domain.SingleValueObject;

import javax.validation.constraints.NotBlank;

public final class UserName extends SingleValueObject<String> {

    public UserName(String value) {
        super(value);
    }

    @NotBlank
    @Override
    public String getValue() {
        return value;
    }

}
