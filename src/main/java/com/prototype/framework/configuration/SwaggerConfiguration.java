package com.prototype.framework.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.core.jackson.ModelResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public ModelResolver createModelResolver(ObjectMapper objectMapper) {
        return new ModelResolver(objectMapper);
    }

}
