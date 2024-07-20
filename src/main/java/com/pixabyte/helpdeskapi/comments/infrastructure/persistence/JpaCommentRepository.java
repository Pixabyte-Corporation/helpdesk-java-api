package com.pixabyte.helpdeskapi.comments.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JpaCommentRepository extends JpaRepository<CommentEntity, UUID> {
    List<CommentEntity> findAllByTicketId(UUID ticketId);
}
