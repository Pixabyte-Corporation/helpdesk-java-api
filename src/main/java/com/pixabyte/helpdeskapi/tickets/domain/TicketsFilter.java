package com.pixabyte.helpdeskapi.tickets.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public record TicketsFilter(
        UUID projectId,
        String status,
        Integer priority,
        LocalDateTime fromDate,
        LocalDateTime toDate
) {
}
