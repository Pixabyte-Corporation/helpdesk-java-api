package com.pixabyte.helpdeskapi.notification.application;

import java.util.UUID;

public record SendVerificationEmailCommand(
        UUID userId,
        String toEmail,
        String fullName
) {
}
