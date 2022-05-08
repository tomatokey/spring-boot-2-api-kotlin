package com.tomatokey.framework.configuration.mvc;

import com.tomatokey.framework.configuration.mvc.auth.AuthorizationHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Bean
    AuthorizationHandlerInterceptor authorizationHandlerInterceptor() {
        return new AuthorizationHandlerInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationHandlerInterceptor())
                .addPathPatterns("/**");
    }

}
