package com.example.notification.dto;

import com.example.notification.enums.NotificationChannel;
import com.example.notification.enums.NotificationType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PreferenceResponse {

    private NotificationType notificationType;
    private NotificationChannel channel;
    private boolean enabled;
}
