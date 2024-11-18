package com.akfc.training;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("jokes", r -> r.path("/api/jokes/random")
                        .filters(f -> f.rewritePath("/api/jokes/random", "/jokes/random"))
                        .uri("https://api.chucknorris.io"))
                .route("movies", r -> r.path("/movies/**")
                        .filters(f -> f.rewritePath("/movies/(?<segment>.*)", "/api/movies/${segment}"))
                        .uri("lb://MOVIES"))
                .build();
    }
}
