package com.pixabyte.helpdeskapi.authentication.infrastructure.controllers;

public record LoginPostRequest(
        String email,
        String password
) {
}
