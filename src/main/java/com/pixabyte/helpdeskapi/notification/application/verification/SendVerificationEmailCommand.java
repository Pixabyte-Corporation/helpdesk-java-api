package com.pixabyte.helpdeskapi.notification.application.verification;

import java.util.UUID;

public record SendVerificationEmailCommand(
        UUID userId,
        String toEmail,
        String fullName
) {
}
