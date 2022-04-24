package com.tomatokey.prototype.domain.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.tomatokey.prototype.domain.ValueObject;
import lombok.Getter;


@Getter
public class UserId implements ValueObject<Integer> {
    
    @JsonValue
    private Integer value;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public UserId(Integer value) {
        this.value = value;
    }

}
