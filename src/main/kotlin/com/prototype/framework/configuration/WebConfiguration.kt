package com.prototype.framework.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.client.RestTemplate

/**
 * spring-web用の設定クラス
 */
@Configuration
class WebConfiguration {

    @Bean
    fun restTemplate(): RestTemplate {
        return RestTemplate()
    }

}