package com.pixabyte.helpdeskapi.comments.application.create;

import java.util.UUID;

public record CreateCommentCommand(
        UUID commentId,
        UUID ownerId,
        String content,
        UUID ticketId,
        UUID parentCommentId
) {
}
