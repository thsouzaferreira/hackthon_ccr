package com.hackathon.ccr.chatbot.repository;

import com.hackathon.ccr.chatbot.domain.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

    Optional<Driver> findByTelephone(String telephone);
}
