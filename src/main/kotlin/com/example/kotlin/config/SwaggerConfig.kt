package com.example.kotlin.config

import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.OpenAPI
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("Course API")
                    .version("1.0.0")
                    .description("This is an Course API documented with Swagger and SpringDoc.")
            )
    }
}