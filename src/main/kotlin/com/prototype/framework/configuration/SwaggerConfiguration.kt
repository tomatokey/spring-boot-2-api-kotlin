package com.prototype.framework.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import io.swagger.v3.core.jackson.ModelResolver
import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfiguration {
    @Bean
    fun createModelResolver(objectMapper: ObjectMapper?): ModelResolver {
        return ModelResolver(objectMapper)
    }

    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .components(
                Components().addSecuritySchemes(
                    "token",
                    SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer")
                )
            )
            .addSecurityItem(SecurityRequirement().addList("token"))
    }
}