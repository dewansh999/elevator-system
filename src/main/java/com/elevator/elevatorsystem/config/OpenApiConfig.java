package com.elevator.elevatorsystem.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI elevatorApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Elevator System API")
                        .description("Elevator scheduling simulation service")
                        .version("1.0"));
    }
}
