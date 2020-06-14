package com.hackathon.ccr.chatbot.controller;

import com.hackathon.ccr.chatbot.domain.Message;
import com.hackathon.ccr.chatbot.service.ChatbotService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chatbot")
@Api(value = "chatbot")
public class ChatbotController {

    @Autowired
    private ChatbotService chatbotService;

    @ApiOperation(value = "Recebe mensagens do WhatsApp usuário")
    @PostMapping(produces = {MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(value = HttpStatus.OK)
    public String receiveMessage(@RequestParam MultiValueMap request) {
        return chatbotService.handleWhatsAppMessage(request);
    }

    @ApiOperation(value = "Notifica um ou mais usuários cadastrados")
    @PostMapping("/notification")
    @ResponseStatus(value = HttpStatus.OK)
    public void notifyUsers(@RequestBody Message message) {
        chatbotService.notifyUser(message);
    }
}
