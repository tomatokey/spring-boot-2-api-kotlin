package com.tomatokey.prototype.domain.models.values;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public class UserName {

    @JsonValue
    private String name;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public UserName(String name) {
        this.name = name;
    }

}
