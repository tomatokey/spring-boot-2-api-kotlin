package com.tomatokey.prototype.configuration.converter.jdbc;


import com.tomatokey.prototype.domain.user.UserId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.util.Objects;

@ReadingConverter
public class UserIdReadConverter implements Converter<Number, UserId> {

    public UserId convert(Number id) {
        return Objects.nonNull(id) ? new UserId(id.intValue()) : null;
    }

}
