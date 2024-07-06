package com.pixabyte.helpdeskapi.authentication.domain;

public class UserAlreadyExists extends RuntimeException {
    private final String message;

    public UserAlreadyExists(String message) {
        this.message = message;
    }

}
