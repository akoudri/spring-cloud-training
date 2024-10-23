package com.akfc.training;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // Route pour l'API Chuck Norris
                .route("chuck-norris", r -> r
                        .path("/api/jokes/random")
                        .filters(f -> f
                                .rewritePath("/api/jokes/random", "/jokes/random"))
                        .uri("https://api.chucknorris.io"))
                .build();
    }
}
