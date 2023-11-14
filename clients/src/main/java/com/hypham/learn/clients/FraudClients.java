package com.hypham.learn.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("fraud")
public interface FraudClients {
  @GetMapping(path = "api/v1/fraud-check/{customerEmail}")
  ResponseEntity<FraudCheckResponse<Boolean>> isFraudster(
      @PathVariable("customerEmail") String customerEmail);
}
