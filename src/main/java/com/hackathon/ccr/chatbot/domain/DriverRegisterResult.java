package com.hackathon.ccr.chatbot.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DriverRegisterResult {

    private Driver driver;

    private boolean isRegisterCompleted;

    private String message;
}
