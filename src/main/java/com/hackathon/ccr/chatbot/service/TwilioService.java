package com.hackathon.ccr.chatbot.service;

import com.twilio.exception.TwilioException;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.twiml.MessagingResponse;
import com.twilio.twiml.messaging.Body;
import com.twilio.twiml.messaging.Message;
import com.twilio.type.PhoneNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TwilioService {

    @Autowired
    private TwilioRestClient client;

    @Value("${app.twilio.telephone.to}")
    private String phoneNumber;

    @Value("${app.twilio.telephone.from}")
    private String twilioPhoneNumber;

    public void sendWhatsAppMessage(String message) {
        MessageCreator messageCreator = new MessageCreator(new PhoneNumber(phoneNumber),
                new PhoneNumber(twilioPhoneNumber), message);
        try {
            messageCreator.create(this.client);
        } catch (TwilioException e) {
            log.error("An exception occurred trying to send a message to {}, exception: {}", phoneNumber,
                    e.getMessage());
            throw e;
        }
    }

    public String createResponseMessage(String message) {
        Body body = new Body
                .Builder(message)
                .build();
        Message sms = new Message
                .Builder()
                .body(body)
                .build();
        MessagingResponse.Builder twiml = new MessagingResponse
                .Builder();
        twiml.message(sms);
        return twiml.build().toXml();
    }

    public String createResponseMessage(List<String> messages) {
        MessagingResponse.Builder twiml = new MessagingResponse
                .Builder();
        messages.stream().forEach(m -> {
            Body body = new Body
                    .Builder(m)
                    .build();
            Message sms = new Message
                    .Builder()
                    .body(body)
                    .build();
            twiml.message(sms);
        });
        return twiml.build().toXml();
    }


}
