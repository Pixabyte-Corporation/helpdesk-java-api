package com.pixabyte.helpdeskapi.authentication.application;

import java.util.UUID;

public record SignupUserRequest(
        UUID organizationId,
        UUID roleId,
        UUID id,
        String email,
        String password,
        String name
) {

}
