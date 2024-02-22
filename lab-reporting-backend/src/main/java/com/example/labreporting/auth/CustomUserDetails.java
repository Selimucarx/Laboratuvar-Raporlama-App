package com.example.labreporting.auth;

import com.example.labreporting.model.RoleEnum;
import com.example.labreporting.model.User;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Set;
import java.util.UUID;

@Data
public class CustomUserDetails implements UserDetails {

    private UUID uuid;
    private String username;
    private String password;
    private boolean accountNonExpired;
    private boolean isEnabled;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private Set<RoleEnum> authorities;

    public static CustomUserDetails fromUser(User user) {
        CustomUserDetails userDetails = new CustomUserDetails();
        userDetails.setUuid(user.getUuid());
        userDetails.setUsername(user.getUsername());
        userDetails.setPassword(user.getPassword());
        userDetails.setAccountNonExpired(true);
        userDetails.setEnabled(true);
        userDetails.setAccountNonLocked(true);
        userDetails.setCredentialsNonExpired(true);
        userDetails.setAuthorities(user.getAuthorities());
        return userDetails;
    }


}
