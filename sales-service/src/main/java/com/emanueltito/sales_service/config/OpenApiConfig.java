package com.emanueltito.sales_service.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Sales Service API",
        version = "1.0",
        description = "Operations related to sales management"
    )
)
public class OpenApiConfig {
}
