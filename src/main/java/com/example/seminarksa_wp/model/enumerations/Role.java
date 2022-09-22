package com.example.seminarksa_wp.model.enumerations;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    ROLE_ADMIN,
    ROLE_MANAGER,
    ROLE_USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
