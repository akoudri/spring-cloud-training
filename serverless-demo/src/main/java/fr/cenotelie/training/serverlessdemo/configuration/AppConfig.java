package fr.cenotelie.training.serverlessdemo.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;


@Slf4j
@Configuration
public class AppConfig {

    @Bean
    public Supplier<String> hello() {
        return () -> "Hello World";
    }

    @Bean
    public Consumer<String> display() {
        return (message) -> log.info("Received message: {}", message);
    }

    @Bean
    public Function<String, String> uppercase() {
        return (message) -> message.toUpperCase();
    }

}
