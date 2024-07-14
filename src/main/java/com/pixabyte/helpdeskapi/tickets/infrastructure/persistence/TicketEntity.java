package com.pixabyte.helpdeskapi.tickets.infrastructure.persistence;

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
@Table(name = "tickets")
public class TicketEntity {

    @Id
    private UUID id;
    private String title;
    private String description;
    private Integer priority;
    private String status;
    private UUID reportToUserId;
    private UUID assignedToUserId;
    private UUID projectId;
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
