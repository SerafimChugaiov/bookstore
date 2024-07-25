package com.manager.bookstore.confing;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfing {


    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .servers(
                        List.of(
                                new Server().url("http://localhost:8080")
                        )
                )
                .info(
                        new Info().title("Book store")
                )
                .addSecurityItem(
                        new SecurityRequirement()
                                .addList("Authentication")
                )
                .components(new Components()
                        .addSecuritySchemes("Authentication",
                                new SecurityScheme()
                                        .name("Authentication")
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("basic"))
                );
    }
}
