package com.hypham.learn.notification;

import com.hypham.learn.clients.dto.CustomerRegisterNotiRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class NotificationService {
    private UserNotificationRepository userNotificationRepository;
    public void registerUser(CustomerRegisterNotiRequest request) {

        UserNotificationModel userNotificationModel  =
                UserNotificationModel.builder()
                        .userName(request.userName())
                        .userEmail(request.userEmail())
                        .userId(request.userId())
                        .build();
        userNotificationRepository.save(userNotificationModel);
    }
}
