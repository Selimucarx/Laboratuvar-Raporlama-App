package com.example.labreporting.exception;

public class UserNotFoundResponse {
    private String message;

    public UserNotFoundResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
