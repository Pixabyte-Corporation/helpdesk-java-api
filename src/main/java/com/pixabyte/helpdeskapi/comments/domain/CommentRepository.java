package com.pixabyte.helpdeskapi.comments.domain;

import com.pixabyte.helpdeskapi.comments.domain.values.CommentId;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CommentRepository {

    void save(Comment c);
    List<Comment> findAll(UUID ticketId);
    Optional<Comment> findById(CommentId id);

}
