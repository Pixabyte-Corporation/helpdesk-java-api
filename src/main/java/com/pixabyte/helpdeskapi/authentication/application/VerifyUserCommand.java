package com.pixabyte.helpdeskapi.authentication.application;

import java.util.UUID;

public record VerifyUserCommand(
        UUID userId
) {
}
