package com.pixabyte.helpdeskapi.comments.infrastructure.persistence;

import com.pixabyte.helpdeskapi.comments.domain.Comment;
import com.pixabyte.helpdeskapi.comments.domain.CommentRepository;
import com.pixabyte.helpdeskapi.comments.domain.values.CommentContent;
import com.pixabyte.helpdeskapi.comments.domain.values.CommentId;
import com.pixabyte.helpdeskapi.comments.domain.values.TicketId;
import com.pixabyte.helpdeskapi.shared.domain.values.LocalTimestamp;
import com.pixabyte.helpdeskapi.shared.domain.values.UserId;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
                .id(UUID.fromString(c.getId().value()))
                .content(c.getContent().value())
                .ownerUserId(UUID.fromString(c.getOwnerId().toString()))
                .createdBy(UUID.fromString(c.getOwnerId().toString()))
                .ticketId(UUID.fromString(c.getTicketId().value()))
                .parentCommentId(UUID.fromString(c.getParentCommentId().value()))
                .createdAt(c.getCreatedAt().toEpochSecond())
                .build();
        jpaCommentRepository.save(entity);
    }

    @Override
    public List<Comment> findAll(UUID ticketId) {
        return jpaCommentRepository.findAllByTicketId(ticketId).stream()
                .map(this::toComment)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Comment> findById(CommentId id) {
        return jpaCommentRepository
                .findById(UUID.fromString(id.value()))
                .map(this::toComment);
    }

    private Comment toComment(CommentEntity entity) {
        return Comment.builder()
                .id(new CommentId(entity.getId().toString()))
                .content(new CommentContent(entity.getContent()))
                .ownerId(new UserId(entity.getOwnerUserId().toString()))
                .ticketId(new TicketId(entity.getTicketId().toString()))
                .parentCommentId(Objects.nonNull(entity.getParentCommentId())? new CommentId(entity.getParentCommentId().toString()): null)
                .createdAt(LocalTimestamp.ofEpochSecond(entity.getCreatedAt()))
                .build();
    }
}
