package com.hackathon.ccr.chatbot.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "telemedicine")
public class Telemedicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = true, name = "request_id")
    private Request request;

    @Column(name = "create_date", nullable = false)
    private LocalDateTime date;

    @Column(name = "schedule_date", nullable = true)
    private String scheduleDate;


}
