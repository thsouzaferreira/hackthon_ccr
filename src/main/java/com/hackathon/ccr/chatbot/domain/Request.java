package com.hackathon.ccr.chatbot.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "request")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = Menu.Converter.class)
    @Column(name = "menu_item", nullable = false)
    private Menu menu;

    @Convert(converter = Status.Converter.class)
    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "create_date", nullable = false)
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = true, name = "driver_id")
    private Driver driver;

}
