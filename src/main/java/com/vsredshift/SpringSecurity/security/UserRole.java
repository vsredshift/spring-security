package com.vsredshift.SpringSecurity.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.vsredshift.SpringSecurity.security.UserPermission.*;

public enum UserRole {
    ADMIN(Sets.newHashSet(
            MEMBER_READ,
            MEMBER_WRITE,
            SUBSCRIPTION_READ,
            SUBSCRIPTION_WRITE)),

    JUNIOR_ADMIN(Sets.newHashSet(
            MEMBER_READ,
            MEMBER_WRITE,
            SUBSCRIPTION_READ
    )),

    SUBSCRIBER(Sets.newHashSet(
            SUBSCRIPTION_READ,
            MEMBER_READ
    )),

    CUSTOMER(Sets.newHashSet(
            MEMBER_READ
    )),
    GUEST(Sets.newHashSet());

    public Set<UserPermission> getPermissions() {
        return permissions;
    }

    private final Set<UserPermission> permissions;

    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(
                        permission.getPermission()
                ))
                .collect(Collectors.toSet());

        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;

    }
}
