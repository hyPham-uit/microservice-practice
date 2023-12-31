package com.hypham.learn.fraud.repository;

import com.hypham.learn.fraud.model.FraudCheckHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FraudCheckHistoryRepository extends JpaRepository<FraudCheckHistory, Integer> {
    Optional<FraudCheckHistory> findByCustomerEmail(String customerEmail);
}
