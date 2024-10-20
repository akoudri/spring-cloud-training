package fr.cenotelie.training.multimedia.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    /*@Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/norris/**")
                        .uri("https://api.chucknorris.io/jokes"))
                .route(r -> r.path("/movies/api/**")
                        .uri("lb://movie"))
                .build();
    }*/
}
