package com.prototype.framework.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

/**
 * spring-security用の設定クラス
 */
@Configuration
@EnableWebSecurity
class WebSecurityConfiguration {

    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http.authorizeRequests()
                .anyRequest().permitAll()
                .and()
                .csrf().disable()
                .httpBasic().disable()
                .build()
    }

    @Bean
    fun userDetailsService(): InMemoryUserDetailsManager {
        return InMemoryUserDetailsManager()
    }
}