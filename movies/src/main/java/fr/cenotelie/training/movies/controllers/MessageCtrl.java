package fr.cenotelie.training.movies.controllers;

import fr.cenotelie.training.movies.dao.MessagesDAO;
import fr.cenotelie.training.movies.streams.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/messages")
public class MessageCtrl {

    @Autowired
    private MessagesDAO messagesDAO;

    @Autowired
    private MessageProducer producer;

    @PostMapping
    public void postMessage(@RequestParam String content) {
        producer.sendMessage(content);
    }
}
