package com.hypham.learn.service;

import com.hypham.learn.dto.CustomerRegistrationRequest;
import com.hypham.learn.model.Customer;
import com.hypham.learn.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public record CustomerService(CustomerRepository customerRepository) {
  public void register(CustomerRegistrationRequest request) {
    Customer customer =
        Customer.builder()
            .firstName(request.firstName())
            .lastName(request.lastName())
            .email(request.email())
            .build();
    customerRepository.save(customer);
    log.info("New customer: {}", customer);
  }
}
