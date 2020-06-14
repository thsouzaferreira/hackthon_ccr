package com.hackathon.ccr.chatbot.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MockData {

    public static final Map<String, String> RESTAURANTS = Collections.unmodifiableMap(
            new HashMap<String, String>() {{
                put("1", "Graal Topázio - SP-330, s/n - Km 140 - Zona Rural, Limeira - SP, 13486-199");
                put("2", "Graal 125 Norte - Santa Bárbara d'Oeste - SP");
                put("3", "Graal 56 (Sul) - Rod. dos Bandeirantes, Km 56 - Chácara Malota, Jundiaí - SP, 13211-510");
            }});
}
