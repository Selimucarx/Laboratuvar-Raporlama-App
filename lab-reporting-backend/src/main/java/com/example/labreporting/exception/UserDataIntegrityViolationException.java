package com.example.labreporting.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class UserDataIntegrityViolationException extends DataIntegrityViolationException {

    public UserDataIntegrityViolationException(String message) {
        super(message);
    }

}
