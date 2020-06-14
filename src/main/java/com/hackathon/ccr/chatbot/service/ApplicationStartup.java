package com.hackathon.ccr.chatbot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private TwilioService twilioService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
      //  twilioService.sendWhatsAppMessage("Teste Java");
    }
}
