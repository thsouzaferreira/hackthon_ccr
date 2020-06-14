package com.hackathon.ccr.chatbot.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "mechanical_emergency")
public class MechanicalEmergency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = true, name = "request_id")
    private Request request;

    @Column(name = "create_date", nullable = false)
    private LocalDateTime date;

    @Column(name = "longitude", nullable = true)
    private String longitude;

    @Column(name = "latitude", nullable = true)
    private String latitude;
}
