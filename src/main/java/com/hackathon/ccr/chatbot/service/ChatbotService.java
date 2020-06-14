package com.hackathon.ccr.chatbot.service;

import com.hackathon.ccr.chatbot.domain.Driver;
import com.hackathon.ccr.chatbot.domain.DriverRegisterResult;
import com.hackathon.ccr.chatbot.domain.Message;
import com.hackathon.ccr.chatbot.repository.DriverRepository;
import com.hackathon.ccr.chatbot.utils.ReservedWords;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;

import java.util.Optional;

@Service
@Slf4j
public class ChatbotService {

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private MessageContentBuilder messageContentBuilder;

    @Autowired
    private MenuService menuService;

    @Autowired
    private TwilioService twilioService;

    @Transactional
    public String handleWhatsAppMessage(MultiValueMap request) {
        return handleIncomingMessage(request);
    }

    private String handleIncomingMessage(MultiValueMap request) {
        String body = (String) request.getFirst("Body");
        String fromTelephone = (String) request.getFirst("From");
        Optional<Driver> driver = driverRepository.findByTelephone(fromTelephone);
        if (!driver.isPresent()) {
            Driver newDriver = new Driver();
            newDriver.setTelephone(fromTelephone);
            driverRepository.save(newDriver);
            return this.twilioService.createResponseMessage(messageContentBuilder.buildFirstGreeting());
        } else {
            Driver registeredDriver = driver.get();
            DriverRegisterResult registerResult = this.handleDriverRegister(registeredDriver, body);
            if (!registerResult.isRegisterCompleted()) {
                return this.twilioService.createResponseMessage(registerResult.getMessage());
            } else {
                if (isGreetingMessage(body.toLowerCase().trim()) || isMenuMessage(body.toLowerCase().trim())) {
                    return this.twilioService.createResponseMessage(messageContentBuilder.buildMainMenu(registeredDriver.getName()));
                } else {
                    return menuService.handleMenuItem(request, registeredDriver);
                }
            }
        }
    }

    private DriverRegisterResult handleDriverRegister(Driver driver, String body) {
        if (StringUtils.isBlank(driver.getName())) {
            driver.setName(body);
            driverRepository.save(driver);
            return DriverRegisterResult.builder().driver(driver).isRegisterCompleted(false).message(messageContentBuilder.buildCpfRegister()).build();
        }
        if (StringUtils.isBlank(driver.getDocument())) {
            driver.setDocument(body);
            driverRepository.save(driver);
            return DriverRegisterResult.builder().driver(driver).isRegisterCompleted(false).message(messageContentBuilder.buildBirthday()).build();

        }
        if (StringUtils.isBlank(driver.getBirthday())) {
            driver.setBirthday(body);
            driverRepository.save(driver);
            return DriverRegisterResult.builder().driver(driver).isRegisterCompleted(false).message(messageContentBuilder.buildAddress()).build();

        }
        if (StringUtils.isBlank(driver.getAddress())) {
            driver.setAddress(body);
            driverRepository.save(driver);
            return DriverRegisterResult.builder().driver(driver).isRegisterCompleted(true).message(messageContentBuilder.buildAddress()).build();
        }
        return DriverRegisterResult.builder().driver(driver).isRegisterCompleted(true).message(messageContentBuilder.buildAddress()).build();
    }


    private boolean isGreetingMessage(String message) {
        return ReservedWords.GREETINGS.contains(message);
    }

    private boolean isMenuMessage(String message) {
        return ReservedWords.MENU.contains(message);
    }


    public void notifyUser(Message message) {
        log.info("Sending message {} to users", message);
        twilioService.sendWhatsAppMessage(message.getMessage());
    }

}
