package com.deskify.configuration;

import org.modelmapper.ModelMapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;


@Configuration
public class AppConfig {

    // ModelMapperConfig
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    // SwaggerConfig
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Deskify API")
                        .version("1.0.0")
                        .description("API documentation for Deskify"));
    }

}