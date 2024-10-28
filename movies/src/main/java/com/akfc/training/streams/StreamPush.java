package com.akfc.training.streams;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class StreamPush {

    @Bean
    public Supplier<String> pushMessage() {
        return () -> "Hello, World!";
    }

}
