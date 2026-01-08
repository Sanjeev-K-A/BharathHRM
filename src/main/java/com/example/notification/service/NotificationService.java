package com.example.notification.service;

import com.example.notification.entity.NotificationDeliveryLog;
import com.example.notification.enums.DeliveryStatus;
import com.example.notification.enums.NotificationChannel;
import com.example.notification.enums.NotificationType;
import com.example.notification.enums.SkipReason;
import com.example.notification.repository.NotificationDeliveryLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationPreferenceService preferenceService;
    private final NotificationDeliveryLogRepository logRepository;

    public void notify(
            Long userId,
            Long organizationId,
            NotificationType type,
            String message
    ) {

        for (NotificationChannel channel : NotificationChannel.values()) {

            boolean enabled =
                    preferenceService.isChannelEnabled(
                            userId, organizationId, type, channel
                    );

            if (!enabled) {
                logSkipped(userId, organizationId, type, channel);
                continue;
            }

            send(userId, message, channel);
            logSent(userId, organizationId, type, channel);
        }
    }

    private void send(Long userId, String message, NotificationChannel channel) {

        if (channel == NotificationChannel.EMAIL) {
            sendEmail(userId, message);
        }

        if (channel == NotificationChannel.IN_APP) {
            createInAppNotification(userId, message);
        }
    }

    private void logSkipped(
            Long userId,
            Long orgId,
            NotificationType type,
            NotificationChannel channel
    ) {

        logRepository.save(
                NotificationDeliveryLog.builder()
                        .userId(userId)
                        .organizationId(orgId)
                        .notificationType(type)
                        .channel(channel)
                        .status(DeliveryStatus.SKIPPED)
                        .reason(SkipReason.USER_DISABLED_CHANNEL)
                        .build()
        );
    }

    private void logSent(
            Long userId,
            Long orgId,
            NotificationType type,
            NotificationChannel channel
    ) {

        logRepository.save(
                NotificationDeliveryLog.builder()
                        .userId(userId)
                        .organizationId(orgId)
                        .notificationType(type)
                        .channel(channel)
                        .status(DeliveryStatus.SENT)
                        .build()
        );
    }

    private void sendEmail(Long userId, String message) {
        // email sender
    }

    private void createInAppNotification(Long userId, String message) {
        // persist in_app notification
    }
}
