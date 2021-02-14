package com.spring.golub.entity;

import org.springframework.security.core.GrantedAuthority;

public enum RoleType implements GrantedAuthority {
    ADMIN,
    USER;


    @Override
    public String getAuthority() {
        return name();
    }
}
