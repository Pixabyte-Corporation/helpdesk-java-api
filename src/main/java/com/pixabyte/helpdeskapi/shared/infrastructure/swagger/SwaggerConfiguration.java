package com.pixabyte.helpdeskapi.shared.infrastructure.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI helpDeskAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Help Desk - Pixabyte API")
                        .version("0.1")
                        .description("API para manejo de incidencias")
                );
    }

}
