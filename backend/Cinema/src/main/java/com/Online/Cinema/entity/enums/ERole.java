package com.Online.Cinema.entity.enums;

import org.springframework.security.core.GrantedAuthority;

public enum ERole implements GrantedAuthority {
    DEFAULT, SUPERUSER;

    @Override
    public String getAuthority() {
        return name();
    }
}
