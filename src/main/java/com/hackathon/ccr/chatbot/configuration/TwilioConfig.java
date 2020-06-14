package com.hackathon.ccr.chatbot.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.twilio.http.TwilioRestClient;

/**
 * Configures the Twilio Rest Client, to send SMS.
 * @author Thiago Souza Ferreira
 */
@Configuration
public class TwilioConfig {

    @Value("${app.twilio.account-sid}")
    String accountSid;

    @Value("${app.twilio.auth-token}")
    String authToken;

    @Bean
    public TwilioRestClient createRestClient() {
        return new TwilioRestClient.Builder(accountSid, authToken).build();
    }

}

