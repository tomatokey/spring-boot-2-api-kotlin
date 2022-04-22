package com.tomatokey.prototype.configuration.converter.jdbc;

import com.tomatokey.prototype.domain.models.values.id.UserId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

import java.math.BigInteger;
import java.util.Objects;


@WritingConverter
public class UserIdWriteConverter implements Converter<UserId, BigInteger> {

    public BigInteger convert(UserId id) {
        return Objects.nonNull(id) ? BigInteger.valueOf(id.getId()) : null;
    }

}
