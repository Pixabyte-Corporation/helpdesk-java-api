package com.pixabyte.helpdeskapi.comments.application.find;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@Builder(toBuilder = true)
public class CommentRepresentation {
    private String id;
    private String content;
    private String ownerId;
    private List<CommentRepresentation> answers;
}
