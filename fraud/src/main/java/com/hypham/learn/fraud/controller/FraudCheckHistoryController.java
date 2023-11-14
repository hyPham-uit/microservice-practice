package com.hypham.learn.fraud.controller;

import com.hypham.learn.fraud.model.FraudCheckHistory;
import com.hypham.learn.fraud.service.FraudCheckService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.hypham.learn.clients.ResponseStatusEnum;
import com.hypham.learn.clients.FraudCheckResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api/v1/fraud-check")
@RequiredArgsConstructor
public class FraudCheckHistoryController {
  private final FraudCheckService fraudCheckService;

  @PostMapping(path = "{customerEmail}")
  public FraudCheckHistory updateFraudStatus(@PathVariable("customerEmail") String customerEmail) {
    log.info("Add fraudster");
    return fraudCheckService.updatedFraudStatus(customerEmail);
  }

  @GetMapping(path = "{customerEmail}")
  public ResponseEntity<FraudCheckResponse<Boolean>> isFraudster(
      @PathVariable("customerEmail") String customerEmail) {
    log.info("Check fraudster");
    return ResponseEntity.ok(
        new FraudCheckResponse<>(
            ResponseStatusEnum.SUCCESS, fraudCheckService.checkFraudster(customerEmail)));
  }
}
