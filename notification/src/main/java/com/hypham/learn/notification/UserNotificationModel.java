package com.hypham.learn.notification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class UserNotificationModel {
    @Id
    @SequenceGenerator(name="user_noti_id_sequence", sequenceName = "user_noti_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_noti_id_sequence")
    private Integer id;

    private String userName;
    private Integer userId;
    private String userEmail;
}
