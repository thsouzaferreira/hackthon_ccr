package com.hackathon.ccr.chatbot.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "driver")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = true)
    private String name;

    @Column(name = "telephone", nullable = true, unique = true)
    private String telephone;

    @Column(name = "birthday", nullable = true)
    private String birthday;

    @Column(name = "document", nullable = true)
    private String document;

    @Column(name = "address", nullable = true)
    private String address;

    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL)
    private List<Session> sessionList;

    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL)
    private List<Request> requests;




}
