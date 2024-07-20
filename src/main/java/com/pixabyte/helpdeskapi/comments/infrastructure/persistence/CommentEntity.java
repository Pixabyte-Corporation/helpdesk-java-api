package com.pixabyte.helpdeskapi.comments.infrastructure.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "comments")
public class CommentEntity {

    @Id
    private UUID id;
    private UUID parentCommentId;
    private String content;
    private UUID ownerUserId;
    private UUID ticketId;
    private Long createdAt;
    private Long updatedAt;
    private UUID createdBy;
    private UUID updatedBy;

    @PrePersist
    public void prepersists() {
        this.createdAt = Instant.now().getEpochSecond();
        this.updatedAt = Instant.now().getEpochSecond();
    }


}
