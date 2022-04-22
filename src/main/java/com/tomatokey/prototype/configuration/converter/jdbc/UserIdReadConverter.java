package com.tomatokey.prototype.configuration.converter.jdbc;

import com.tomatokey.prototype.domain.models.values.id.UserId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.math.BigInteger;
import java.util.Objects;

@ReadingConverter
public class UserIdReadConverter implements Converter<BigInteger, UserId> {

    public UserId convert(BigInteger id) {
        return Objects.nonNull(id) ? new UserId(id.longValue()) : null;
    }

}
