package com.pixabyte.helpdeskapi.comments.infrastructure.persistence;

import com.pixabyte.helpdeskapi.comments.domain.Comment;
import com.pixabyte.helpdeskapi.comments.domain.CommentRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class PostgresCommentRepository implements CommentRepository {

    private final JpaCommentRepository jpaCommentRepository;

    public PostgresCommentRepository(JpaCommentRepository jpaCommentRepository) {
        this.jpaCommentRepository = jpaCommentRepository;
    }

    @Override
    public void save(Comment c) {
        var entity = CommentEntity.builder()
                .id(c.getId())
                .content(c.getContent())
                .ownerUserId(c.getOwnerId())
                .createdBy(c.getOwnerId())
                .ticketId(c.getTicketId())
                .parentCommentId(c.getParentCommentId())
                .build();
        jpaCommentRepository.save(entity);
    }

    @Override
    public List<Comment> findAll(UUID ticketId) {
        return jpaCommentRepository.findAllByTicketId(ticketId).stream()
                .map(this::toComment)
                .collect(Collectors.toList());
    }

    private Comment toComment(CommentEntity entity) {
        return Comment.builder()
                .id(entity.getId())
                .content(entity.getContent())
                .ownerId(entity.getOwnerUserId())
                .ticketId(entity.getTicketId())
                .parentCommentId(entity.getParentCommentId())
                .build();
    }
}
