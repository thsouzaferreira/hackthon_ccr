package com.hackathon.ccr.chatbot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
public class MessageContentBuilder {

    @Autowired
    @Qualifier("textTemplateEngine")
    TemplateEngine messageTemplate;

    public String buildFirstGreeting() {
        final Context context = new Context();
        return messageTemplate.process("greetingCcr", context);
    }

    public String buildCpfRegister() {
        final Context context = new Context();
        return messageTemplate.process("cpf", context);
    }

    public String buildBirthday() {
        final Context context = new Context();
        return messageTemplate.process("dataNascimento", context);
    }

    public String buildAddress() {
        final Context context = new Context();
        return messageTemplate.process("endereco", context);
    }

    public String buildMainMenu(String name) {
        final Context context = new Context();
        context.setVariable("name", name);
        return messageTemplate.process("menu", context);
    }

    public String buildHealthSubmenu() {
        final Context context = new Context();
        return messageTemplate.process("submenuSaude", context);
    }

    public String buildInfoSubmenu() {
        final Context context = new Context();
        return messageTemplate.process("submenuInformacoes", context);
    }

    public String buildPartnersSubmenu() {
        final Context context = new Context();
        return messageTemplate.process("submenuParcerias", context);
    }

    public String buildSosSubmenu() {
        final Context context = new Context();
        return messageTemplate.process("submenuSocorro", context);
    }

    public String buildTelemedicineConfirmation(String name, String document, String date) {
        final Context context = new Context();
        context.setVariable("name", name);
        context.setVariable("document", document);
        context.setVariable("date", date);
        return messageTemplate.process("telemedicineConfirmation", context);
    }

    public String buildRestaurantVoucherConfirmation(String name, String document, String voucher) {
        final Context context = new Context();
        context.setVariable("name", name);
        context.setVariable("document", document);
        context.setVariable("voucher", voucher);
        return messageTemplate.process("voucherRestaurant", context);
    }

    public String buildMechanicalEmergencyConfirmation(String name, String document, String longitude, String latitude) {
        final Context context = new Context();
        context.setVariable("name", name);
        context.setVariable("document", document);
        context.setVariable("longitude", longitude);
        context.setVariable("latitude", latitude);
        return messageTemplate.process("mechanicalEmergencyConfirmation", context);
    }
}
