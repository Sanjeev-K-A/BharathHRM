package com.example.notification.service;

import com.example.notification.entity.NotificationPreference;
import com.example.notification.enums.NotificationChannel;
import com.example.notification.enums.NotificationType;
import com.example.notification.repository.NotificationPreferenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationPreferenceService {

    private final NotificationPreferenceRepository repository;

    public boolean isChannelEnabled(
            Long userId,
            Long organizationId,
            NotificationType type,
            NotificationChannel channel
    ) {

        if (userId != null) {
            var userPref = repository
                    .findByUserIdAndOrganizationIdAndNotificationTypeAndChannel(
                            userId, organizationId, type, channel);

            if (userPref.isPresent()) {
                return userPref.get().isEnabled();
            }
        }

        var orgPref = repository
                .findByUserIdIsNullAndOrganizationIdAndNotificationTypeAndChannel(
                        organizationId, type, channel);

        return orgPref.map(NotificationPreference::isEnabled).orElse(true);
    }

    public void savePreference(NotificationPreference pref) {
        repository.save(pref);
    }
}
