package com.pixabyte.helpdeskapi.comments.application;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@Builder(toBuilder = true)
public class CommentRepresentation {
    private UUID id;
    private String content;
    private UUID ownerId;
    private List<CommentRepresentation> answers;
}
