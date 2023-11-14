package com.hypham.fraud.controller;

import com.hypham.fraud.dto.FraudCheckResponse;
import com.hypham.fraud.dto.ResponseStatusEnum;
import com.hypham.fraud.model.FraudCheckHistory;
import com.hypham.fraud.service.FraudCheckService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/fraud-check")
@RequiredArgsConstructor
public class FraudCheckHistoryController {
  private final FraudCheckService fraudCheckService;
  @PostMapping(path = "{customerEmail}")
  public FraudCheckHistory updateFraudStatus(
      @PathVariable("customerEmail") String customerEmail) {
    log.info("Add fraudster");
    return fraudCheckService.updatedFraudStatus(customerEmail);
  }

  @GetMapping(path = "{customerEmail}")
  public ResponseEntity<FraudCheckResponse<Boolean>> isFraudster(@PathVariable("customerEmail") String customerEmail) {
    log.info("Check fraudster");
    return ResponseEntity.ok(new FraudCheckResponse<Boolean>(ResponseStatusEnum.SUCCESS, fraudCheckService.checkFraudster(customerEmail)));
  }
}
