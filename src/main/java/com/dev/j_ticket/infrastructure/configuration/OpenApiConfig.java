package com.dev.j_ticket.infrastructure.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenAPI (Swagger) Documentation Configuration.
 * This class configures the Swagger UI and API documentation. 
 */
@Configuration
public class OpenApiConfig {

	/**
     * Customizes the OpenAPI definition with metadata.
     * * @return the configured OpenAPI object with J-Ticket specifications.
     */
    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
        		.info(new Info()
        			    .title("J-Ticket API")
        			    .version("1.0")
        			    .description("Ticket Management System for events"))
	            .components(new Components()
	                .addSecuritySchemes("basicAuth", 
	                    new SecurityScheme()
	                        .type(SecurityScheme.Type.HTTP)
	                        .scheme("basic")))
	            .addSecurityItem(new SecurityRequirement().addList("basicAuth"));
    }
}