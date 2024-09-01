package com.pixabyte.helpdeskapi.comments.domain;

import com.pixabyte.helpdeskapi.comments.domain.events.CommentCreatedEvent;
import com.pixabyte.helpdeskapi.comments.domain.events.CommentUpdatedEvent;
import com.pixabyte.helpdeskapi.comments.domain.values.CommentContent;
import com.pixabyte.helpdeskapi.comments.domain.values.CommentId;
import com.pixabyte.helpdeskapi.comments.domain.values.TicketId;
import com.pixabyte.helpdeskapi.shared.domain.AggregateRoot;
import com.pixabyte.helpdeskapi.shared.domain.values.LocalTimestamp;
import com.pixabyte.helpdeskapi.shared.domain.values.UserId;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;
import java.util.UUID;

@Getter
@Builder
public class Comment extends AggregateRoot {

    private CommentId id;
    private CommentContent content;
    private TicketId ticketId;
    private UserId ownerId;
    private CommentId parentCommentId;
    private LocalTimestamp createdAt;


    public static Comment createComment(CommentId id, CommentContent content, TicketId ticketId, UserId ownerId, CommentId parentCommentId) {
        Comment c = Comment.builder()
                .id(id)
                .content(content)
                .createdAt(LocalTimestamp.now())
                .ownerId(ownerId)
                .ticketId(ticketId)
                .parentCommentId(parentCommentId)
                .build();
        CommentCreatedEvent commentCreatedEvent = new CommentCreatedEvent(
                UUID.fromString(id.value()),
                content.value(),
                ownerId.value(),
                ticketId.value(),
                parentCommentId.value());
        c.recordEvent(commentCreatedEvent);
        return c;
    }

    public void update(CommentContent content) {
        CommentContent previousContent = this.content;
        this.content = content;
        var event = new CommentUpdatedEvent(
                UUID.fromString(id.value()),
                content.value(),
                previousContent.value(),
                ownerId.value(),
                ticketId.value()
        );
        recordEvent(event);
    }

    public Map<String, Object> toPrimitives() {
        return null;
    }

}
