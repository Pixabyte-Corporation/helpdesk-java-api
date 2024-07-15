package com.pixabyte.helpdeskapi.tickets.infrastructure.controllers;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(description = "Object used to create a new ticket")
public record CreateTicketPutRequest(
        @Schema(description = "Ticket ID", example = "3c794037-32fb-498a-a0e8-377a41281ae4")
        UUID id,
        @Schema(description = "Ticket title")
        String title,
        @Schema(description = "Ticket description")
        String description,
        @Schema(description = "Ticket status", allowableValues = {"CREATED", "IN_PROGRESS"})
        String status,
        @Schema(description = "Ticket priority")
        Integer priority,
        @Schema(description = "Ticket assignee user id")
        UUID assignedTo

) {
}
