package com.pixabyte.helpdeskapi.comments.infrastructure.controllers.create;

import java.util.UUID;

public record CreateCommentPutRequest(
        String commentId,
        String parentCommentId,
        String content
) {
}
