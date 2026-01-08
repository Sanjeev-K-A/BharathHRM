package com.example.notification.security;

public class AuthUser {

    private final Long userId;
    private final Long organizationId;

    public AuthUser(Long userId, Long organizationId) {
        this.userId = userId;
        this.organizationId = organizationId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getOrganizationId() {
        return organizationId;
    }
}
