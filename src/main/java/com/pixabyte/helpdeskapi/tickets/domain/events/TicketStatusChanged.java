package com.pixabyte.helpdeskapi.tickets.domain.events;

import com.pixabyte.helpdeskapi.shared.domain.DomainEvent;

import java.util.Map;
import java.util.UUID;

public class TicketStatusChanged extends DomainEvent {

    private final String EVENT_NAME = "ticketStatusChanged";
    private final String previousStatus;
    private final String currentStatus;
    private final UUID ticketId;
    private final UUID modifiedByUUID;

    public TicketStatusChanged(String previousStatus, String currentStatus, UUID ticketId, UUID modifiedByUUID) {
        super(ticketId);
        this.previousStatus = previousStatus;
        this.currentStatus = currentStatus;
        this.ticketId = ticketId;
        this.modifiedByUUID = modifiedByUUID;
    }

    @Override
    public String getEventName() {
        return EVENT_NAME;
    }

    @Override
    public Map<String, Object> toPrimitives() {
        return null;
    }
}
