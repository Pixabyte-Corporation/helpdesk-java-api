package com.pixabyte.helpdeskapi.comments.application.update;

import com.pixabyte.helpdeskapi.comments.domain.values.CommentContent;
import com.pixabyte.helpdeskapi.comments.domain.values.CommentId;

public record UpdateCommentCommand(
        CommentId commentId,
        CommentContent content
) {
}
