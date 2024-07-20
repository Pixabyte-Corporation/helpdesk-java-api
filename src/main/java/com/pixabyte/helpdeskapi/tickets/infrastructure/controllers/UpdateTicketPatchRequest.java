package com.pixabyte.helpdeskapi.tickets.infrastructure.controllers;

import java.util.UUID;

public record UpdateTicketPatchRequest(
        UUID assigneeId,
        String title,
        String description,
        String status,
        Integer priority
) {
}
