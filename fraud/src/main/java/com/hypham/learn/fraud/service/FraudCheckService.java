package com.hypham.learn.fraud.service;

import com.hypham.learn.fraud.model.FraudCheckHistory;
import com.hypham.learn.fraud.repository.FraudCheckHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FraudCheckService {
  private final FraudCheckHistoryRepository fraudCheckHistoryRepository;
  public FraudCheckHistory updatedFraudStatus(String customerEmail) {
    return fraudCheckHistoryRepository.save(
        FraudCheckHistory.builder()
            .customerEmail(customerEmail)
            .createdAt(LocalDateTime.now())
            .build());
  }

  public Boolean checkFraudster(String customerEmail) {
    return fraudCheckHistoryRepository.findByCustomerEmail(customerEmail).isPresent();
  }
}
