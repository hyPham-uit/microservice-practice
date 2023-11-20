package com.hypham.learn.notification;

import com.hypham.learn.clients.dto.CustomerRegisterNotiRequest;
import com.hypham.learn.clients.dto.DefaultResponse;
import com.hypham.learn.clients.dto.ResponseStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/notification")
public class NotificationController {
  @Autowired private NotificationService notificationService;

  @PostMapping(path = "customer/register")
  public ResponseEntity<DefaultResponse<Boolean>> userRegister(
      @RequestBody CustomerRegisterNotiRequest customerRegisterNotiRequest) {
    log.info("Send noti controller");
    notificationService.registerUser(customerRegisterNotiRequest);
    return ResponseEntity.ok(new DefaultResponse<>(ResponseStatusEnum.SUCCESS, true));
  }
}
