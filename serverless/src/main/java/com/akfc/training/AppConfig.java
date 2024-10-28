package com.akfc.training;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class AppConfig {

    @Bean
    public Supplier<String> message() {
        return () -> "Hello, World!";
    }

    @Bean
    public Consumer<String> print() {
        return (String message) -> System.out.println(message);
    }

    @Bean
    public Function<String, String> upperCase() {
        return (String message) -> message.toUpperCase();
    }

}
