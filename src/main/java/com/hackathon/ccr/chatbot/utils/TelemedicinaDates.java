package com.hackathon.ccr.chatbot.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TelemedicinaDates {

    public static final Map<String, String> TELEMEDICINE_DATES = Collections.unmodifiableMap(
            new HashMap<String, String>() {{
                put("100", "15/06/2020 15:00:00");
                put("101", "15/06/2020 15:30:00");
                put("102", "15/06/2020 16:00:00");
            }});
}
