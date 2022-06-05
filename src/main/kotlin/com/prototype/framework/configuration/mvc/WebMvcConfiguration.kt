package com.prototype.framework.configuration.mvc

import com.prototype.framework.configuration.mvc.auth.AuthorizationHandlerInterceptor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebMvcConfiguration : WebMvcConfigurer {

    @Bean
    fun authorizationHandlerInterceptor(): AuthorizationHandlerInterceptor {
        return AuthorizationHandlerInterceptor()
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(authorizationHandlerInterceptor())
                .addPathPatterns("/**")
    }

}