package com.hypham.learn.customer.service;

import com.hypham.learn.clients.NotificationClients;
import com.hypham.learn.clients.dto.CustomerRegisterNotiRequest;
import com.hypham.learn.customer.repository.CustomerRepository;
import com.hypham.learn.customer.dto.CustomerRegistrationRequest;
import com.hypham.learn.customer.model.Customer;
import com.hypham.learn.clients.dto.DefaultResponse;
import com.hypham.learn.clients.FraudClients;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
// @RequiredArgsConstructor
@AllArgsConstructor
public class CustomerService {
  //  @Value("${services.fraud.v1.endpoint}")
  //  private String fraudEndpoint;

  private final CustomerRepository customerRepository;
  //  private final RestTemplate restTemplate;
  @Autowired private final FraudClients fraudClients;
  @Autowired private final NotificationClients notificationClients;

  public void register(CustomerRegistrationRequest request) {
    Customer customer =
        Customer.builder()
            .firstName(request.firstName())
            .lastName(request.lastName())
            .email(request.email())
            .build();
    //    FraudCheckResponse<Boolean> fraudCheckResponse =
    //        restTemplate.getForObject(
    //            //            "http://localhost:8081/api/v1/fraud-check/{customerId}",
    //            // FraudCheckResponse.class, customer.getId());
    //            fraudEndpoint.concat("{customerEmail}"), FraudCheckResponse.class,
    // customer.getEmail());

    ResponseEntity<DefaultResponse<Boolean>> fraudCheckResponse =
        fraudClients.isFraudster(customer.getEmail());
    log.info("fraud result: {}", fraudCheckResponse.getBody());
    if (Objects.requireNonNull(fraudCheckResponse.getBody()).getBody()) {
      throw new IllegalStateException("fraudster!");
    }
    customerRepository.saveAndFlush(customer);
    fraudClients.isFraudster(customer.getEmail());
    ResponseEntity<DefaultResponse<Boolean>> isSendNoti = notificationClients.userRegister(
        CustomerRegisterNotiRequest.builder()
            .userId(customer.getId())
            .userEmail(customer.getEmail())
            .userName(customer.getFirstName())
            .build());
    log.info("New customer: {}", customer);
    if (!Objects.requireNonNull(isSendNoti.getBody()).getBody()){
      throw new IllegalStateException("Send notification fail!");
    }
  }
}
