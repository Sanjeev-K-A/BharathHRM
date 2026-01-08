package com.example.notification.controller;

import com.example.notification.dto.PreferenceUpdateRequest;
import com.example.notification.entity.NotificationPreference;
import com.example.notification.service.NotificationPreferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/org/notification-preferences")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ORG_ADMIN')")
public class OrgNotificationPreferenceController {

    private final NotificationPreferenceService service;

    @GetMapping
    public List<NotificationPreference> getOrgDefaults(
            @RequestHeader("X-ORG-ID") Long organizationId
    ) {
        return service.findOrgDefaults(organizationId);
    }

    @PutMapping
    public void updateOrgPreference(
            @RequestHeader("X-ORG-ID") Long organizationId,
            @RequestBody PreferenceUpdateRequest request
    ) {
        service.upsert(
                NotificationPreference.builder()
                        .organizationId(organizationId)
                        .userId(null)
                        .notificationType(request.getNotificationType())
                        .channel(request.getChannel())
                        .enabled(request.isEnabled())
                        .build()
        );
    }
}
