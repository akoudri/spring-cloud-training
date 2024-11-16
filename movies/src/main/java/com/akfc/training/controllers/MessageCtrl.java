package com.akfc.training.controllers;

import com.akfc.training.services.MessageService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/messages")
public class MessageCtrl {

    private final MessageService messageService;

    public MessageCtrl(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public String getMessage() {
        return messageService.getMessage();
    }
}
