package com.pixabyte.helpdeskapi.tickets.domain;

import com.pixabyte.helpdeskapi.shared.domain.DomainEvent;

import java.util.Map;
import java.util.UUID;

public class TicketUpdated extends DomainEvent {
    private String title;
    private String description;
    private Integer priority;
    private String status;
    private UUID assignedToId;

    public TicketUpdated(UUID aggregateId, String title, String description, Integer priority, String status, UUID assignedToId) {
        super(aggregateId);
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.assignedToId = assignedToId;
    }

    @Override
    public String getEventName() {
        return "ticketUpdated";
    }

    @Override
    public Map<String, Object> toPrimitives() {
        return null;
    }
}
