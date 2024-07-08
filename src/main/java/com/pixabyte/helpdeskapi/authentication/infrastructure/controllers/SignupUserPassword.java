package com.pixabyte.helpdeskapi.authentication.infrastructure.controllers;

public class SignupUserPassword {
    private final String value;

    public SignupUserPassword(String value) {

        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
