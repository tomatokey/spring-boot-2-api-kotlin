package com.tomatokey.prototype.domain.models.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.tomatokey.prototype.domain.models.ValueObject;
import lombok.Getter;
import org.springframework.data.relational.core.mapping.Column;

import static com.tomatokey.prototype.domain.models.user.User.COLUMN_NAME_USER_ID;


@Getter
public class UserId implements ValueObject<Integer> {


    @JsonValue
    private Integer value;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public UserId(Integer value) {
        this.value = value;
    }

}
