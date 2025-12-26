package com.example.notification.controller;

import com.example.notification.dto.PreferenceUpdateRequest;
import com.example.notification.entity.NotificationPreference;
import com.example.notification.service.NotificationPreferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/org/notification-preferences")
@RequiredArgsConstructor
public class OrgNotificationPreferenceController {

    private final NotificationPreferenceService service;

    @PutMapping
    public void updateOrgPreference(
            @RequestParam Long organizationId,
            @RequestBody PreferenceUpdateRequest request
    ) {

        NotificationPreference pref = NotificationPreference.builder()
                .organizationId(organizationId)
                .userId(null)
                .notificationType(request.getNotificationType())
                .channel(request.getChannel())
                .enabled(request.isEnabled())
                .build();

        service.savePreference(pref);
    }
}
