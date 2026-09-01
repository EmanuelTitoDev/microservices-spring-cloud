package com.emanueltito.products_service.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Products Service API",
        version = "1.0",
        description = "Operations related to product management"
    )
)
public class OpenApiConfig {
}
