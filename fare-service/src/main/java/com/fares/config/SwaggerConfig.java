package com.fares.config;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI fareServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                    .title("Flight Booking Fare Service API")
                    .version("1.0.0")
                    .description("API for calculating flight fares and managing promo codes")
                    .contact(new Contact()
                        .name("Flight Booking Support")
                        .email("support@flightbooking.com")
                        .url("https://www.flightbooking.com/support")
                    )
                );
    }
}