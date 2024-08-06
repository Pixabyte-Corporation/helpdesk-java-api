package com.pixabyte.helpdeskapi.tickets.application.create;

import java.util.UUID;

public record CreateTicketCommand(
        UUID ticketId,
        UUID reporterId,
        UUID assignedTo,
        UUID projectId,
        String title,
        String description,
        String status,
        Integer priority
) {
}
