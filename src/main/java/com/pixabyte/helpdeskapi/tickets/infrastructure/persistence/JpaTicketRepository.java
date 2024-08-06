package com.pixabyte.helpdeskapi.tickets.infrastructure.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.UUID;

public interface JpaTicketRepository extends JpaRepository<TicketEntity, UUID>, JpaSpecificationExecutor<TicketEntity> {
    Page<TicketEntity> findAllByIsArchived(Boolean archived, Specification<TicketEntity> spec, Pageable pageable);
}
