package com.pixabyte.helpdeskapi.notification.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder(toBuilder = true)
public class EmailProperties {
    private UUID userId;
    private String subject;
    private String toEmail;
}
