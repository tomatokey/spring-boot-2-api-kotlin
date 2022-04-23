package com.tomatokey.prototype.domain.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.tomatokey.prototype.domain.ValueObject;
import lombok.Getter;

@Getter
public class UserName implements ValueObject<String> {

    @JsonValue
    private String value;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public UserName(String value) {
        this.value = value;
    }

}
