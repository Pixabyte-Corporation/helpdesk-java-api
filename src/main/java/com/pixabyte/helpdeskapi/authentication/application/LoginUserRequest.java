package com.pixabyte.helpdeskapi.authentication.application;

public record LoginUserRequest(
        String email,
        String password
) {
}
