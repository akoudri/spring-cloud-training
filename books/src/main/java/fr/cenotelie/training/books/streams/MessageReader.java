package fr.cenotelie.training.books.streams;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;
import java.util.function.Function;

@Slf4j
@Service
public class MessageReader {

    @Bean
    public Consumer<String> consumeMessages() {
        return message -> log.info("Received message: {}", message);
    }

    @Bean
    public Function<String, String> transformMessages() {
        return message -> message.toUpperCase();
    }
}
