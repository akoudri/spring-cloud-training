package com.akfc.training.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Value("${message.greeting}")
    private String message;

    public String getMessage() {
        return message;
    }

}
