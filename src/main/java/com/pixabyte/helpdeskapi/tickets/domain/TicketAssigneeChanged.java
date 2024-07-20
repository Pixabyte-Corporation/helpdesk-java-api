package com.pixabyte.helpdeskapi.tickets.domain;

import com.pixabyte.helpdeskapi.shared.domain.DomainEvent;

import java.util.Map;
import java.util.UUID;

public class TicketAssigneeChanged extends DomainEvent {

    private final String EVENT_NAME = "ticketAssigneeChanged";
    private final UUID previousAssignee;
    private final UUID currentAssignee;
    private final UUID ticketId;
    private final UUID modifiedByUUID;

    public TicketAssigneeChanged(UUID previousAssignee, UUID currentAssignee, UUID ticketId, UUID modifiedByUUID) {
        super(ticketId);
        this.previousAssignee = previousAssignee;
        this.currentAssignee = currentAssignee;
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
