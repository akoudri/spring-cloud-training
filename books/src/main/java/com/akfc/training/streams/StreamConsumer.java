package com.akfc.training.streams;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;
import java.util.function.Function;

@Service
@Slf4j
public class StreamConsumer {

    @Bean
    public Consumer<String> consumeMessage() {
        return message -> log.info("Consumed message: {}", message);
    }

    @Bean
    public Function<String, String> transformMessage() {
        return String::toUpperCase;
    }
}
