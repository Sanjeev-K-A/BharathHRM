package com.example.notification.controller;

import com.example.notification.dto.PreferenceUpdateRequest;
import com.example.notification.entity.NotificationPreference;
import com.example.notification.service.NotificationPreferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/me/notification-preferences")
@RequiredArgsConstructor
public class UserNotificationPreferenceController {

    private final NotificationPreferenceService service;

    @PutMapping
    public void updateUserPreference(
            @RequestParam Long userId,
            @RequestParam Long organizationId,
            @RequestBody PreferenceUpdateRequest request
    ) {

        NotificationPreference pref = NotificationPreference.builder()
                .userId(userId)
                .organizationId(organizationId)
                .notificationType(request.getNotificationType())
                .channel(request.getChannel())
                .enabled(request.isEnabled())
                .build();

        service.savePreference(pref);
    }
}
