package com.tomatokey.prototype.domain.models.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.tomatokey.prototype.domain.models.ValueObject;
import lombok.Getter;
import org.springframework.data.relational.core.mapping.Column;

@Getter
public class UserName implements ValueObject<String> {

    @JsonValue
    private String value;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public UserName(String value) {
        this.value = value;
    }

}
