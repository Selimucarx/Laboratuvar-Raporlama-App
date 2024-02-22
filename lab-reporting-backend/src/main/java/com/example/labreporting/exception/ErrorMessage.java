package com.example.labreporting.exception;

import lombok.Getter;

@Getter
public enum ErrorMessage {
    USER_NOT_FOUND("User not found."),
    DATA_INTEGRITY_VIOLATION("Data integrity violation occurred."),
    GENERAL_EXCEPTION("An unexpected error occurred.");


    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

}
