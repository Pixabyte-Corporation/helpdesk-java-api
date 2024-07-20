package com.pixabyte.helpdeskapi.comments.infrastructure.controllers;

import java.util.UUID;

public record CreateCommentPutRequest(
        UUID commentId,
        UUID parentCommentId,
        String content
) {
}
