package com.example.notification.repository;

import com.example.notification.entity.NotificationPreference;
import com.example.notification.enums.NotificationChannel;
import com.example.notification.enums.NotificationType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NotificationPreferenceRepository
        extends JpaRepository<NotificationPreference, Long> {

    Optional<NotificationPreference> findByUserIdAndOrganizationIdAndNotificationTypeAndChannel(
            Long userId,
            Long organizationId,
            NotificationType notificationType,
            NotificationChannel channel
    );

    Optional<NotificationPreference> findByUserIdIsNullAndOrganizationIdAndNotificationTypeAndChannel(
            Long organizationId,
            NotificationType notificationType,
            NotificationChannel channel
    );

    List<NotificationPreference> findByOrganizationIdAndUserIdIsNull(Long organizationId);

    List<NotificationPreference> findByUserId(Long userId);
}
