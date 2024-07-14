package com.pixabyte.helpdeskapi.tickets.infrastructure.persistence;

import com.pixabyte.helpdeskapi.shared.domain.ResultsPage;
import com.pixabyte.helpdeskapi.tickets.domain.TicketPagination;
import com.pixabyte.helpdeskapi.tickets.domain.TicketsFilter;
import com.pixabyte.helpdeskapi.tickets.domain.Ticket;
import com.pixabyte.helpdeskapi.tickets.domain.TicketRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class PostgresTicketRepository implements TicketRepository {

    private final JpaTicketRepository jpaTicketRepository;

    public PostgresTicketRepository(JpaTicketRepository jpaTicketRepository) {
        this.jpaTicketRepository = jpaTicketRepository;
    }

    @Override
    public void save(Ticket ticket) {
        TicketEntity entity = TicketEntity.builder()
                .id(ticket.getId())
                .title(ticket.getTitle())
                .description(ticket.getDescription())
                .status(ticket.getStatus())
                .priority(ticket.getPriority())
                .projectId(ticket.getProjectId())
                .reportToUserId(ticket.getReporterId())
                .assignedToUserId(ticket.getAssignedToId())
                .updatedBy(ticket.getAssignedToId())
                .createdBy(ticket.getReporterId())
                .build();
        jpaTicketRepository.save(entity);
    }

    @Override
    public ResultsPage<Ticket> findAll(TicketsFilter filter, TicketPagination pagination) {
        Specification<TicketEntity> spec = createSpecification(filter);

        PageRequest pageRequest = PageRequest.of(
                pagination.pageNumber(),
                pagination.pageSize()
        );

        var pageTickets = jpaTicketRepository.findAll(spec, pageRequest)
                .map(this::toTicket);
        return new ResultsPage<Ticket>(
                pageTickets.getContent(),
                pageTickets.getNumber(),
                pageTickets.getSize(),
                pageTickets.getTotalElements()
        );
    }

    private Ticket toTicket(TicketEntity entity) {
        return Ticket.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .status(entity.getStatus())
                .priority(entity.getPriority())
                .reporterId(entity.getReportToUserId())
                .assignedToId(entity.getAssignedToUserId())
                .projectId(entity.getProjectId())
                .build();
    }

    private Specification<TicketEntity> createSpecification(TicketsFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (Objects.nonNull(filter.projectId())) {
                predicates.add(cb.equal(root.get("projectId"), filter.projectId()));
            }
            if (Objects.nonNull(filter.status())) {
                predicates.add(cb.equal(root.get("status"), filter.status()));
            }

            if (Objects.nonNull(filter.priority())) {
                predicates.add(cb.equal(root.get("priority"), filter.priority()));
            }

            if (Objects.nonNull(filter.fromDate())) {
                Long epoch = filter.fromDate().toEpochSecond(ZoneOffset.UTC);
                predicates.add(cb.greaterThanOrEqualTo(root.get("createdAt"), epoch));
            }
            if (Objects.nonNull(filter.toDate())) {
                Long epoch = filter.toDate().toEpochSecond(ZoneOffset.UTC);
                predicates.add(cb.lessThanOrEqualTo(root.get("createdAt"), epoch));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }


}
