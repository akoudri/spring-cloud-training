package com.akfc.training.services;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EventsListener {

    @EventListener
    public void onApplicationStarted(ApplicationStartedEvent event) {
        // Code à exécuter au démarrage
    }
}
