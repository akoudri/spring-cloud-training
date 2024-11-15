package com.akfc.training.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EventsListener {

    @EventListener
    public void onApplicationStarted(ApplicationStartedEvent event) {
        log.info("Application started");
    }
}
