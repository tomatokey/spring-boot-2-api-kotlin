package com.tomatokey.prototype.configuration.converter.jdbc;

import com.tomatokey.prototype.domain.models.user.UserName;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

import java.util.Objects;


@WritingConverter
public class UserNameWriteConverter implements Converter<UserName, String> {

    public String convert(UserName userName) {
        return Objects.nonNull(userName) ? userName.getValue() : null;
    }

}
