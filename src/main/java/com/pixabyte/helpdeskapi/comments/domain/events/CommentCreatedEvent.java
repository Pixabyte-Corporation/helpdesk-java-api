package com.pixabyte.helpdeskapi.comments.domain.events;

import com.pixabyte.helpdeskapi.shared.domain.DomainEvent;

import java.util.Map;
import java.util.UUID;

public class CommentCreatedEvent extends DomainEvent {

    private final String EVENT_NAME = "commentCreated";
    private final String content;
    private final String ownerId;
    private final String ticketId;
    private final String parentCommentId;


    public CommentCreatedEvent(UUID aggregateId,
                               String content,
                               String ownerId,
                               String ticketId,
                               String parentCommentId) {
        super(aggregateId);
        this.content = content;
        this.ownerId = ownerId;
        this.ticketId = ticketId;
        this.parentCommentId = parentCommentId;
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
