package com.pixabyte.helpdeskapi.authentication.infrastructure.controllers;

import com.pixabyte.helpdeskapi.authentication.application.SignupUserRequest;

import java.util.UUID;

public record SignupPostRequest(
        UUID organizationId,
        UUID roleId,
        UUID id,
        String email,
        String password,
        String name
) {

}
