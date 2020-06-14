package com.hackathon.ccr.chatbot.service;

import com.hackathon.ccr.chatbot.domain.*;
import com.hackathon.ccr.chatbot.repository.MechanicalEmergencyRepository;
import com.hackathon.ccr.chatbot.repository.RequestRepository;
import com.hackathon.ccr.chatbot.repository.RestaurantVoucherRepository;
import com.hackathon.ccr.chatbot.repository.TelemedicineRepository;
import com.hackathon.ccr.chatbot.utils.MockData;
import com.hackathon.ccr.chatbot.utils.TelemedicinaDates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class MenuService {

    @Autowired
    private TwilioService twilioService;

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private TelemedicineRepository telemedicineRepository;

    @Autowired
    private RestaurantVoucherRepository restaurantVoucherRepository;

    @Autowired
    private MessageContentBuilder messageContentBuilder;

    @Autowired
    private MechanicalEmergencyRepository mechanicalEmergencyRepository;

    public String handleMenuItem(MultiValueMap request, Driver driver) {
        String body = (String) request.getFirst("Body");
        Optional<Request> openRequest = driver.getRequests().stream().filter(request1 -> request1.getStatus().equals(Status.OPEN)).findFirst();
        if (openRequest.isPresent()) {
            Request userRequest = openRequest.get();
            Menu menu = userRequest.getMenu();
            if (menu.equals(Menu.TELEMEDICINA)) {
                Telemedicine telemedicine = telemedicineRepository.findByRequest(userRequest).get();
                telemedicine.setScheduleDate(TelemedicinaDates.TELEMEDICINE_DATES.get(body));
                telemedicineRepository.save(telemedicine);
                userRequest.setStatus(Status.CLOSED);
                requestRepository.save(userRequest);
                return twilioService.createResponseMessage(messageContentBuilder.buildTelemedicineConfirmation(driver.getName(), driver.getDocument(), telemedicine.getScheduleDate()));
            }
            if (menu.equals(Menu.ALIMENTACAO)) {
                RestaurantVoucher restaurantVoucher = restaurantVoucherRepository.findByRequest(userRequest).get();
                restaurantVoucher.setSelectedRestaurant(MockData.RESTAURANTS.get(body));
                restaurantVoucher.setVoucher(String.valueOf(new Random().nextInt(10000)));
                restaurantVoucherRepository.save(restaurantVoucher);
                userRequest.setStatus(Status.CLOSED);
                requestRepository.save(userRequest);
                return twilioService.createResponseMessage(messageContentBuilder.buildRestaurantVoucherConfirmation(driver.getName(), driver.getDocument(), restaurantVoucher.getVoucher()));
            }
            if (menu.equals(Menu.MECANICO)) {
                MechanicalEmergency mechanicalEmergency = mechanicalEmergencyRepository.findByRequest(userRequest).get();
                mechanicalEmergency.setLatitude((String) request.getFirst("Latitude"));
                mechanicalEmergency.setLongitude((String) request.getFirst("Longitude"));
                mechanicalEmergencyRepository.save(mechanicalEmergency);
                userRequest.setStatus(Status.CLOSED);
                requestRepository.save(userRequest);
                return twilioService.createResponseMessage(messageContentBuilder.buildMechanicalEmergencyConfirmation(driver.getName(),
                        driver.getDocument(), mechanicalEmergency.getLongitude(), mechanicalEmergency.getLatitude()));
            }

        }
        Menu menu = this.retrieveMenuItem(body.trim());
        if (menu.isHasSubmenu()) {
            if (menu.equals(Menu.SAUDE)) {
                return twilioService.createResponseMessage(messageContentBuilder.buildHealthSubmenu());
            }
            if (menu.equals(Menu.PARCERIAS)) {
                return twilioService.createResponseMessage(messageContentBuilder.buildPartnersSubmenu());
            }

            if (menu.equals(Menu.INFO)) {
                return twilioService.createResponseMessage(messageContentBuilder.buildInfoSubmenu());
            }
            if (menu.equals(Menu.SOCORRO)) {
                return twilioService.createResponseMessage(messageContentBuilder.buildSosSubmenu());
            }
        } else {
            Request newRequest = new Request();
            newRequest.setDate(LocalDateTime.now());
            newRequest.setMenu(menu);
            newRequest.setDriver(driver);
            newRequest.setStatus(Status.OPEN);
            Request savedRequest = this.requestRepository.save(newRequest);
            if (menu.equals(Menu.TELEMEDICINA)) {
                Telemedicine telemedicine = new Telemedicine();
                telemedicine.setDate(LocalDateTime.now());
                telemedicine.setRequest(savedRequest);
                this.telemedicineRepository.save(telemedicine);
                List<String> messages = new ArrayList<>();
                messages.add("Selecione uma das seguintes datas:");
                TelemedicinaDates.TELEMEDICINE_DATES.forEach((key, value) -> {
                    messages.add(key.concat(" ").concat("-").concat(" ").concat(value));
                });
                return this.twilioService.createResponseMessage(messages);
            }
            if (menu.equals(Menu.ALIMENTACAO)) {
                RestaurantVoucher restaurantVoucher = new RestaurantVoucher();
                restaurantVoucher.setDate(LocalDateTime.now());
                restaurantVoucher.setRequest(savedRequest);
                this.restaurantVoucherRepository.save(restaurantVoucher);
                List<String> messages = new ArrayList<>();
                messages.add("Nós da CCR estamos sempre buscando parceiros para lhe oferecer descontos em alimentação.");
                messages.add("Selecione um dos restaurantes abaixo e receba um vaucher de desconto:");
                MockData.RESTAURANTS.forEach((key, value) -> {
                    messages.add(key.concat(" ").concat("-").concat(" ").concat(value));
                });
                return this.twilioService.createResponseMessage(messages);
            }

            if (menu.equals(Menu.MECANICO)) {
                MechanicalEmergency mechanicalEmergency = new MechanicalEmergency();
                mechanicalEmergency.setDate(LocalDateTime.now());
                mechanicalEmergency.setRequest(savedRequest);
                this.mechanicalEmergencyRepository.save(mechanicalEmergency);
                List<String> messages = new ArrayList<>();
                messages.add("Por gentileza nos informe a sua localização");
                return this.twilioService.createResponseMessage(messages);
            }
        }
        return null;
    }

    private Menu retrieveMenuItem(String body) {
        return Menu.forValue(Integer.valueOf(body));
    }
}
