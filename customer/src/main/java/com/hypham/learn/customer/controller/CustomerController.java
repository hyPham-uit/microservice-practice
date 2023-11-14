package com.hypham.learn.customer.controller;

import com.hypham.learn.customer.dto.CustomerRegistrationRequest;
import com.hypham.learn.customer.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {
  @Autowired
  private CustomerService customerService;
  @PostMapping
  public void register(@RequestBody CustomerRegistrationRequest customerRegistrationRequest) {
    log.info("New customer registration {}", customerRegistrationRequest);
    customerService.register(customerRegistrationRequest);
  }
}
