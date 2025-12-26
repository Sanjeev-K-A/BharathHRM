package com.example.notification.dto;

import com.example.notification.enums.NotificationChannel;
import com.example.notification.enums.NotificationType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PreferenceUpdateRequest {

    private NotificationType notificationType;
    private NotificationChannel channel;
    private boolean enabled;
}
