package com.hackathon.ccr.chatbot.repository;

import com.hackathon.ccr.chatbot.domain.MechanicalEmergency;
import com.hackathon.ccr.chatbot.domain.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MechanicalEmergencyRepository extends JpaRepository<MechanicalEmergency, Long> {

    public Optional<MechanicalEmergency> findByRequest(Request request);
}

