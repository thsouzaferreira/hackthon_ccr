package com.hackathon.ccr.chatbot.repository;

import com.hackathon.ccr.chatbot.domain.Request;
import com.hackathon.ccr.chatbot.domain.Telemedicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TelemedicineRepository extends JpaRepository<Telemedicine, Long> {

    public Optional<Telemedicine> findByRequest(Request request);
}
