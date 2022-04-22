package com.tomatokey.prototype.domain.models.values.id;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public class UserId implements ID<Long> {

    @JsonValue
    private Long id;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public UserId(Long id) {
        this.id = id;
    }

}
