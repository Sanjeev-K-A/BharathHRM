package com.example.notification.service;

import com.example.notification.enums.NotificationChannel;
import com.example.notification.enums.NotificationType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationPreferenceService preferenceService;

    public void sendNotification(
            Long userId,
            Long organizationId,
            NotificationType type,
            String message
    ) {

        if (preferenceService.isChannelEnabled(
                userId, organizationId, type, NotificationChannel.EMAIL)) {
            sendEmail(userId, message);
        } else {
            logSkipped(userId, type, NotificationChannel.EMAIL);
        }

        if (preferenceService.isChannelEnabled(
                userId, organizationId, type, NotificationChannel.IN_APP)) {
            createInAppNotification(userId, message);
        } else {
            logSkipped(userId, type, NotificationChannel.IN_APP);
        }
    }

    private void sendEmail(Long userId, String message) {
        System.out.println("EMAIL sent to user " + userId);
    }

    private void createInAppNotification(Long userId, String message) {
        System.out.println("IN_APP notification for user " + userId);
    }

    private void logSkipped(Long userId, NotificationType type, NotificationChannel channel) {
        System.out.println("Skipped " + channel + " for user " + userId + " type " + type);
    }
}
