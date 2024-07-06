package com.pixabyte.helpdeskapi.authentication.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaRoleRepository extends JpaRepository<RoleEntity, UUID> {
}
