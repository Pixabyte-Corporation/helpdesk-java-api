package com.pixabyte.helpdeskapi.authentication.application.login;

public record LoginUserRequest(
        String email,
        String password
) {
}
