package com.pixabyte.helpdeskapi.projects.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaProjectCounterRepository extends JpaRepository<ProjectCounterEntity, UUID> {

}
