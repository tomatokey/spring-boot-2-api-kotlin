package com.tomatokey.prototype.configuration.converter.jdbc;

import com.tomatokey.prototype.domain.models.values.id.RoleId;
import com.tomatokey.prototype.domain.models.values.id.UserId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.Objects;

@WritingConverter
public class RoleIdWriteConverter implements Converter<RoleId, BigInteger> {

    public BigInteger convert(RoleId id) {
        return Objects.nonNull(id) ? BigInteger.valueOf(id.getId()) : null;
    }

}
