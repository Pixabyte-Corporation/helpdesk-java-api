package com.pixabyte.helpdeskapi.comments.domain;

import com.pixabyte.helpdeskapi.shared.domain.DomainEvent;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Builder
public class Comment {

    private UUID id;
    private String content;
    private LocalDateTime createdAt;
    private UUID ticketId;
    private UUID ownerId;
    private UUID parentCommentId;

    private List<DomainEvent> events;

    public static Comment createComment(UUID id, String content, UUID ticketId, UUID ownerId, UUID parentCommentId) {
        Comment c = Comment.builder()
                .id(id)
                .content(content)
                .createdAt(LocalDateTime.now(ZoneId.of("UTC")))
                .ownerId(ownerId)
                .ticketId(ticketId)
                .parentCommentId(parentCommentId)
                .build();
        if (Objects.isNull(c.getEvents())) {
            c.events = new ArrayList<>();
        }
        CommentCreatedEvent commentCreatedEvent = new CommentCreatedEvent(
                id,
                content,
                ownerId,
                ticketId,
                parentCommentId);
        c.events.add(commentCreatedEvent);
        return c;
    }

    public List<DomainEvent> pullEvents() {
        List<DomainEvent> pulledEvents = events;
        events = new ArrayList<>();
        return pulledEvents;
    }

}
