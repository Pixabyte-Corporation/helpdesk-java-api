package com.pixabyte.helpdeskapi.projects.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaProjectRepository extends JpaRepository<ProjectEntity, UUID> {

}
