package com.pixabyte.helpdeskapi.comments.domain;

import com.pixabyte.helpdeskapi.shared.domain.AggregateRoot;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Getter
@Builder
public class Comment extends AggregateRoot {

    private UUID id;
    private String content;
    private LocalDateTime createdAt;
    private UUID ticketId;
    private UUID ownerId;
    private UUID parentCommentId;

    public static Comment createComment(UUID id, String content, UUID ticketId, UUID ownerId, UUID parentCommentId) {
        Comment c = Comment.builder()
                .id(id)
                .content(content)
                .createdAt(LocalDateTime.now(ZoneId.of("UTC")))
                .ownerId(ownerId)
                .ticketId(ticketId)
                .parentCommentId(parentCommentId)
                .build();
        CommentCreatedEvent commentCreatedEvent = new CommentCreatedEvent(
                id,
                content,
                ownerId,
                ticketId,
                parentCommentId);
        c.recordEvent(commentCreatedEvent);
        return c;
    }


}
