package com.pixabyte.helpdeskapi.comments.domain;

import java.util.List;
import java.util.UUID;

public interface CommentRepository {

    void save(Comment c);
    List<Comment> findAll(UUID ticketId);

}
