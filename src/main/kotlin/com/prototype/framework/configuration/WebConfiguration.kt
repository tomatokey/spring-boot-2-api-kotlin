package com.prototype.framework.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
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