package com.hackathon.ccr.chatbot.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "restaurant_voucher")
public class RestaurantVoucher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = true, name = "request_id")
    private Request request;

    @Column(name = "create_date", nullable = false)
    private LocalDateTime date;

    @Column(name = "voucher", nullable = true)
    private String voucher;

    @Column(name = "selected_restaurant", nullable = true)
    private String selectedRestaurant;
}
