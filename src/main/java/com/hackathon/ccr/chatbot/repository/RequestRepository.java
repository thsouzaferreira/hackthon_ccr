package com.hackathon.ccr.chatbot.repository;

import com.hackathon.ccr.chatbot.domain.Driver;
import com.hackathon.ccr.chatbot.domain.Menu;
import com.hackathon.ccr.chatbot.domain.Request;
import com.hackathon.ccr.chatbot.domain.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

    public Optional<Request> findByMenuAndStatusAndDriver(Menu menu, Status status, Driver driver);
}
