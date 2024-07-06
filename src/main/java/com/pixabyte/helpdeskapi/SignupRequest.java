package com.pixabyte.helpdeskapi;

import java.util.UUID;

public record SignupRequest(
        UUID organizationId,
        UUID roleId,
        UUID userId,
        String email,
        String password,
        String name
) {
}
