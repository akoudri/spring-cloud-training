package com.akfc.training.controllers;

import com.akfc.training.dao.MessagesDAO;
import com.akfc.training.streams.MessageProducer;
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
