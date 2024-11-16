package com.akfc.training.services;

import com.akfc.training.configuration.MessagesConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class MessageService {

    @Autowired
    private MessagesConfig config;

    @GetMapping("/message")
    public String getMessage() {
        return config.getGreetings();
    }

}
