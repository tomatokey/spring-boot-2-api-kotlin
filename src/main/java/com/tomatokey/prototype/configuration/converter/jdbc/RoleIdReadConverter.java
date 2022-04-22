package com.tomatokey.prototype.configuration.converter.jdbc;

import com.tomatokey.prototype.domain.models.values.id.RoleId;
import com.tomatokey.prototype.domain.models.values.id.UserId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.Objects;

@ReadingConverter
public class RoleIdReadConverter implements Converter<BigInteger, RoleId> {

    public RoleId convert(BigInteger id) {
        return Objects.nonNull(id) ? new RoleId(id.longValue()) : null;
    }

}
