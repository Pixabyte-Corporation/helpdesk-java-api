package com.pixabyte.helpdeskapi.tickets.domain;

import com.pixabyte.helpdeskapi.shared.domain.DomainEvent;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Builder(toBuilder = true)
public class Ticket {
    private UUID id;
    private String title;
    private String description;
    private Integer priority;
    private String status;
    private UUID reporterId;
    private UUID assignedToId;
    private UUID projectId;

    private List<DomainEvent> events;

    public static Ticket createTicket(UUID id, String title, String description, Integer priority, String status, UUID reporterId, UUID assignedToId, UUID projectId) {
        Ticket ticket = Ticket.builder()
                .id(id)
                .title(title)
                .description(description)
                .status(status)
                .priority(priority)
                .projectId(projectId)
                .reporterId(reporterId)
                .assignedToId(assignedToId)
                .build();
        if (Objects.isNull(ticket.events)) {
            ticket.events = new ArrayList<>();
        }
        var event = new TicketCreated(
                id,
                title,
                description,
                priority,
                status,
                reporterId,
                assignedToId,
                projectId
        );
        ticket.events.add(event);
        return ticket;
    }

    public void changeStatus(String status, UUID modifiedByUUID) {
        if (Objects.isNull(this.events)) {
            this.events = new ArrayList<>();
        }
        if (status.equals(this.status)) {
            return;
        }
        String previousStatus = this.status;
        this.status = status;
        TicketStatusChanged event = new TicketStatusChanged(
                previousStatus,
                status,
                this.id,
                modifiedByUUID
        );
        this.events.add(event);
    }

    public void changeAssignee(UUID assignee, UUID modifiedByUUID) {
        if (Objects.isNull(this.events)) {
            this.events = new ArrayList<>();
        }
        if (Objects.isNull(assignee) && Objects.isNull(assignedToId)) {
            return;
        }

        if (Objects.nonNull(assignee) && assignee.equals(getAssignedToId())) {
            return;
        }
        UUID previousAssignee = this.assignedToId;
        this.assignedToId = assignee;
        TicketAssigneeChanged ticketAssigneeChanged = new TicketAssigneeChanged(
                previousAssignee,
                assignee,
                this.id,
                modifiedByUUID
        );
        this.events.add(ticketAssigneeChanged);
    }


    public List<DomainEvent> pullEvents() {
        if (Objects.isNull(events)) {
            events = new ArrayList<>();
        }
        List<DomainEvent> pulledEvents = new ArrayList<>(events);
        events.clear();
        return pulledEvents;
    }


}
