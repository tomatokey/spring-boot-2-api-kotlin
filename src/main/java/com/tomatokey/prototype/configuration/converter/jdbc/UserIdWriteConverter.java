package com.tomatokey.prototype.configuration.converter.jdbc;

import com.tomatokey.prototype.domain.models.user.UserId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

import java.util.Objects;


@WritingConverter
public class UserIdWriteConverter implements Converter<UserId, Number> {

    public Number convert(UserId id) {
        return Objects.nonNull(id) ? id.getValue() : null;
    }

}
