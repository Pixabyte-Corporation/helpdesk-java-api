package com.pixabyte.helpdeskapi.tickets.application;

import java.util.UUID;

public record UpdateTicketCommand(
        UUID ticketId,
        UUID assignedTo,
        String title,
        String description,
        String status,
        Integer priority,
        UUID mmodifiedByUUID
) {
}
