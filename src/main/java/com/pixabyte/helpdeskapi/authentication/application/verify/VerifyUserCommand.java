package com.pixabyte.helpdeskapi.authentication.application.verify;

import java.util.UUID;

public record VerifyUserCommand(
        UUID userId
) {
}
