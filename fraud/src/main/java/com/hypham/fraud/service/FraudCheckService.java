package com.hypham.fraud.service;

import com.hypham.fraud.model.FraudCheckHistory;
import com.hypham.fraud.repository.FraudCheckHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

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
