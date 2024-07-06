package com.pixabyte.helpdeskapi;

public record AuthenticateRequest(
        String email,
        String password
) {
}
