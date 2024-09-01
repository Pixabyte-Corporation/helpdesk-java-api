package com.pixabyte.helpdeskapi.comments.domain.events;

import com.pixabyte.helpdeskapi.shared.domain.DomainEvent;

import java.util.Map;
import java.util.UUID;

public class CommentUpdatedEvent extends DomainEvent {
    private final String EVENT_NAME = "commentUpdated";
    private final String newContent;
    private final String previousContent;
    private final String ownerId;
    private final String ticketId;

    public CommentUpdatedEvent(UUID aggregateId, String content, String previousContent, String ownerId, String ticketId) {
        super(aggregateId);
        this.newContent = content;
        this.previousContent = previousContent;
        this.ownerId = ownerId;
        this.ticketId = ticketId;
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
