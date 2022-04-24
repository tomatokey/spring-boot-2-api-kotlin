package com.tomatokey.prototype.configuration;

import com.tomatokey.prototype.configuration.converter.UserNameConverter;
import com.tomatokey.prototype.configuration.converter.UserIdConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;

import java.util.Arrays;
import java.util.List;

@EnableJdbcAuditing // @CreatedDateや@LastModifiedDateのために付与
@Configuration
public class JdbcConfiguration extends AbstractJdbcConfiguration {

    @Override
    protected List<?> userConverters() {
        return Arrays.asList(
                new UserIdConverter(),
                new UserNameConverter()
        );
    }

}
