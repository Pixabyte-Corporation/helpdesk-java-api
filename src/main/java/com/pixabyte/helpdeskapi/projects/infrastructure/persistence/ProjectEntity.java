package com.pixabyte.helpdeskapi.projects.infrastructure.persistence;

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
@Table(name = "projects")
public class ProjectEntity {

    @Id
    private UUID id;
    private String name;
    private String description;
    private UUID organizationId;
    private Long createdAt;
    private Long updatedAt;
    private UUID createdBy;
    private UUID updatedBy;

    @PrePersist
    public void prepersist() {
        this.createdAt = Instant.now().getEpochSecond();
        this.updatedAt = Instant.now().getEpochSecond();
    }

}
