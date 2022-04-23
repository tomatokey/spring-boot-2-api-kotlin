package com.tomatokey.prototype.configuration;

import com.tomatokey.prototype.configuration.converter.jdbc.UserIdReadConverter;
import com.tomatokey.prototype.configuration.converter.jdbc.UserIdWriteConverter;
import com.tomatokey.prototype.configuration.converter.jdbc.UserNameReadConverter;
import com.tomatokey.prototype.configuration.converter.jdbc.UserNameWriteConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;

import java.util.Arrays;
import java.util.List;

@EnableJdbcAuditing // @CreatedDateや@LastModifiedDateのために付与
@Configuration
public class CustomJdbcConfiguration extends AbstractJdbcConfiguration {

    @Override
    protected List<?> userConverters() {
        return Arrays.asList(
                new UserIdReadConverter(),
                new UserIdWriteConverter(),
                new UserNameReadConverter(),
                new UserNameWriteConverter()
        );
    }

}
