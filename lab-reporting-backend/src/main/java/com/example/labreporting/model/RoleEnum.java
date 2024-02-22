package com.example.labreporting.model;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Getter
public enum RoleEnum implements GrantedAuthority {

    ROLE_USER("USER"),
    ROLE_ADMIN("ADMIN");

    private String value;

    RoleEnum(String value){
        this.value = value;
    }


    @Override
    public String getAuthority() {
        return name();
    }
}
