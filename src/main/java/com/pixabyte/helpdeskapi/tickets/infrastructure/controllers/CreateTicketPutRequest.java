package com.pixabyte.helpdeskapi.tickets.infrastructure.controllers;

import java.util.UUID;

public record CreateTicketPutRequest(
        UUID id,
        String title,
        String description,
        String status,
        Integer priority,
        UUID assignedTo

) {
}
