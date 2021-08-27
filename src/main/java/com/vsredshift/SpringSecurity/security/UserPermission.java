package com.vsredshift.SpringSecurity.security;

public enum UserPermission {
    MEMBER_READ("member:read"),
    MEMBER_WRITE("member:write"),
    SUBSCRIPTION_READ("subscription:read"),
    SUBSCRIPTION_WRITE("subscription:write");

    private final String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
