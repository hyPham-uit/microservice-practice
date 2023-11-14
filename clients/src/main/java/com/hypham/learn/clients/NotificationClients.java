package com.hypham.learn.clients;

import com.hypham.learn.clients.dto.CustomerRegisterNotiRequest;
import com.hypham.learn.clients.dto.DefaultResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("notification")
public interface NotificationClients {
  @PostMapping(path = "api/v1/notification/customer/register")
  ResponseEntity<DefaultResponse<Boolean>> userRegister(
      @RequestBody CustomerRegisterNotiRequest customerRegisterNotiRequest);
}
