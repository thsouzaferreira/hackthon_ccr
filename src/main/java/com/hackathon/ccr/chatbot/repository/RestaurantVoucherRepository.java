package com.hackathon.ccr.chatbot.repository;

import com.hackathon.ccr.chatbot.domain.Request;
import com.hackathon.ccr.chatbot.domain.RestaurantVoucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantVoucherRepository extends JpaRepository<RestaurantVoucher, Long> {

    public Optional<RestaurantVoucher> findByRequest(Request request);
}
