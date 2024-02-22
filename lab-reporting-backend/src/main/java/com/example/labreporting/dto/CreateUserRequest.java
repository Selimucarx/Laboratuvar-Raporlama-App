package com.example.labreporting.dto;

import com.example.labreporting.model.RoleEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;


@Getter
@Setter
@Builder
@ToString
public class CreateUserRequest {

    private String username;
    private String password;
    private Set<RoleEnum> authorities;

    public CreateUserRequest() {
    }

    @Builder
    public CreateUserRequest(String username, String password, Set<RoleEnum> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Set<RoleEnum> getAuthorities() {
        return authorities;
    }
}
