package com.pixabyte.helpdeskapi.comments.application.create;

import com.pixabyte.helpdeskapi.comments.domain.values.CommentContent;
import com.pixabyte.helpdeskapi.comments.domain.values.CommentId;
import com.pixabyte.helpdeskapi.comments.domain.values.TicketId;
import com.pixabyte.helpdeskapi.shared.domain.values.UserId;

public record CreateCommentCommand(
        CommentId commentId,
        UserId ownerId,
        CommentContent content,
        TicketId ticketId,
        CommentId parentCommentId
) {
}
