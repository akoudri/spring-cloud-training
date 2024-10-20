package fr.cenotelie.training.movies.streams;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class MessageWriter {

    @Bean
    public Supplier<String> produceMessages() {
        return () -> "Hello World";
    }

}
