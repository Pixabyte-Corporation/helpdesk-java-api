package com.pixabyte.helpdeskapi.comments.domain;

import com.pixabyte.helpdeskapi.shared.domain.DomainEvent;

import java.util.Map;
import java.util.UUID;

public class CommentCreatedEvent extends DomainEvent {

    private final String EVENT_NAME = "commentCreated";
    private final String content;
    private final UUID ownerId;
    private final UUID ticketId;
    private final UUID parentCommentId;


    public CommentCreatedEvent(UUID aggregateId,
                               String content,
                               UUID ownerId,
                               UUID ticketId,
                               UUID parentCommentId) {
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
