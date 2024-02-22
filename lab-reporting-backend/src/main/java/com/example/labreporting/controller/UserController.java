package com.example.labreporting.controller;

import com.example.labreporting.dto.AuthRequest;
import com.example.labreporting.dto.CreateUserRequest;
import com.example.labreporting.model.User;
import com.example.labreporting.service.JwtService;
import com.example.labreporting.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private static final Logger log = Logger.getLogger(UserController.class.getName());

    private final UserService userService;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public UserController(UserService userService, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Transactional
    @PostMapping("/register")
    public User addUser(@RequestBody CreateUserRequest request) {
        return userService.createUser(request);
    }


    @PostMapping("/login")
    public String generateToken(@RequestBody AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(request.getUsername());
        }

        log.info("invalid username " + request.getUsername());
        throw new UsernameNotFoundException("invalid username {} " + request.getUsername());
    }



}