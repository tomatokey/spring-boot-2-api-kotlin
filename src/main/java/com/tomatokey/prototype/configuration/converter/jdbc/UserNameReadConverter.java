package com.tomatokey.prototype.configuration.converter.jdbc;

import com.tomatokey.prototype.domain.user.UserName;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
public class UserNameReadConverter implements Converter<String, UserName> {

    public UserName convert(String name) {
        return new UserName(name);
    }

}
