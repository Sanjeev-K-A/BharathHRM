package com.example.notification.service;

import com.example.notification.entity.NotificationPreference;
import com.example.notification.enums.NotificationChannel;
import com.example.notification.enums.NotificationType;
import com.example.notification.repository.NotificationPreferenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationPreferenceService {

    private final NotificationPreferenceRepository repository;

    boolean isChannelEnabled(
            Long userId,
            Long organizationId,
            NotificationType type,
            NotificationChannel channel
    ) {

        if (userId != null) {
            var userPref =
                    repository.findByUserIdAndOrganizationIdAndNotificationTypeAndChannel(
                            userId, organizationId, type, channel
                    );

            if (userPref.isPresent()) {
                return userPref.get().isEnabled();
            }
        }

        var orgPref =
                repository.findByUserIdIsNullAndOrganizationIdAndNotificationTypeAndChannel(
                        organizationId, type, channel
                );

        return orgPref.map(NotificationPreference::isEnabled)
                .orElse(true);
    }

    public void upsert(NotificationPreference incoming) {

        var existing =
                repository.findByUserIdAndOrganizationIdAndNotificationTypeAndChannel(
                        incoming.getUserId(),
                        incoming.getOrganizationId(),
                        incoming.getNotificationType(),
                        incoming.getChannel()
                );

        if (existing.isPresent()) {
            existing.get().setEnabled(incoming.isEnabled());
            repository.save(existing.get());
        } else {
            repository.save(incoming);
        }
    }

    public List<NotificationPreference> findOrgDefaults(Long orgId) {
        return repository.findByOrganizationIdAndUserIdIsNull(orgId);
    }

    public List<NotificationPreference> findUserPrefs(Long userId) {
        return repository.findByUserId(userId);
    }
}
