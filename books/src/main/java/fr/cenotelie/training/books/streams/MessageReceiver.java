package fr.cenotelie.training.books.streams;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageReceiver {

    public void receiveMessage(String message) {
        log.info("Received message: {}", message);
    }

}
