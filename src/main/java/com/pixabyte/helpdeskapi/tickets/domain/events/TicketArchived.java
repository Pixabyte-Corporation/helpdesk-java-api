package com.pixabyte.helpdeskapi.tickets.domain.events;

import com.pixabyte.helpdeskapi.shared.domain.DomainEvent;

import java.util.Map;
import java.util.UUID;

public class TicketArchived extends DomainEvent {

    private final String ticketId;
    private final String ticketTitle;
    private final String archivedByUserId;

    public TicketArchived(String ticketId, String ticketTitle, String archivedByUserId) {
        super(UUID.fromString(ticketId));
        this.ticketId = ticketId;
        this.ticketTitle = ticketTitle;
        this.archivedByUserId = archivedByUserId;
    }


    @Override
    public String getEventName() {
        return null;
    }

    @Override
    public Map<String, Object> toPrimitives() {
        return null;
    }
}
