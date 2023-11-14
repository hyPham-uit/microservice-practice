package com.hypham.customer.service;

import com.hypham.customer.repository.CustomerRepository;
import com.hypham.customer.dto.CustomerRegistrationRequest;
import com.hypham.customer.dto.FraudCheckResponse;
import com.hypham.customer.model.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {
  @Value("${services.fraud.v1.endpoint}")
  private String fraudEndpoint;

  private final CustomerRepository customerRepository;
  private final RestTemplate restTemplate;

  public void register(CustomerRegistrationRequest request) {
    Customer customer =
        Customer.builder()
            .firstName(request.firstName())
            .lastName(request.lastName())
            .email(request.email())
            .build();
    FraudCheckResponse<Boolean> fraudCheckResponse =
        restTemplate.getForObject(
            //            "http://localhost:8081/api/v1/fraud-check/{customerId}",
            // FraudCheckResponse.class, customer.getId());
            fraudEndpoint.concat("{customerEmail}"), FraudCheckResponse.class, customer.getEmail());
    log.info("fraud result: {}", fraudCheckResponse.getBody());
    if (fraudCheckResponse.getBody()) {
      throw new IllegalStateException("fraudster!");
    }
    customerRepository.save(customer);
    log.info("New customer: {}", customer);
  }
}
