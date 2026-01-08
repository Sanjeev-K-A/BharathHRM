package com.example.notification.controller;

import com.example.notification.dto.PreferenceUpdateRequest;
import com.example.notification.entity.NotificationPreference;
import com.example.notification.security.AuthUser;
import com.example.notification.service.NotificationPreferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/me/notification-preferences")
@RequiredArgsConstructor
public class UserNotificationPreferenceController {

    private final NotificationPreferenceService service;

    @GetMapping
    public List<NotificationPreference> getUserPrefs(
            @AuthenticationPrincipal AuthUser user
    ) {
        return service.findUserPrefs(user.getUserId());
    }

    @PutMapping
    public void updateUserPreference(
            @AuthenticationPrincipal AuthUser user,
            @RequestBody PreferenceUpdateRequest request
    ) {

        service.upsert(
                NotificationPreference.builder()
                        .userId(user.getUserId())
                        .organizationId(user.getOrganizationId())
                        .notificationType(request.getNotificationType())
                        .channel(request.getChannel())
                        .enabled(request.isEnabled())
                        .build()
        );
    }
}
