package com.pixabyte.helpdeskapi;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class UserCredentials {

    @Id
    private UUID id;
    private String name;
    private String email;
    private String password;
    private UUID organizationId;
    private UUID roleId;
    private Long createdAt;
    private Long updatedAt;

    @PrePersist()
    public void prepersist() {
        this.createdAt = Instant.now().getEpochSecond();
        this.updatedAt = Instant.now().getEpochSecond();
    }
}
