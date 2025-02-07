package com.kernel360.boogle.global.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Boogle API Documentation")
                        .description("Boogle API 문서")
                        .version("1.0.0")
                        .contact(new Contact().name("Boogle Support").email("support@boogle.com")));
    }
}