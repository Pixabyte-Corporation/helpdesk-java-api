package com.pixabyte.helpdeskapi.tickets.domain;

import com.pixabyte.helpdeskapi.shared.domain.AggregateRoot;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;
import java.util.UUID;

@Getter
@Builder(toBuilder = true)
public class Ticket extends AggregateRoot {
    private UUID id;
    private String title;
    private String description;
    private Integer priority;
    private TicketStatus status;
    private UUID reporterId;
    private UUID assignedToId;
    private UUID projectId;

    public static Ticket createTicket(UUID id, String title, String description, Integer priority, TicketStatus status, UUID reporterId, UUID assignedToId, UUID projectId) {
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

        var event = new TicketCreated(
                id,
                title,
                description,
                priority,
                status.getValue(),
                reporterId,
                assignedToId,
                projectId
        );
        ticket.recordEvent(event);
        return ticket;
    }

    public void update(String title, String description, TicketStatus ticketStatus, UUID assignedTo, Integer priority, UUID modifiedBy) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        changeStatus(ticketStatus, modifiedBy);
        changeAssignee(assignedTo, modifiedBy);
        TicketUpdated event = new TicketUpdated(
                id,
                this.title,
                this.description,
                this.priority,
                this.status.getValue(),
                this.assignedToId
        );
        recordEvent(event);
    }


    private void changeStatus(TicketStatus status, UUID modifiedByUUID) {
        if (this.status.equals(status)) {
            return;
        }
        TicketStatus previousStatus = this.status;
        this.status = status;
        TicketStatusChanged event = new TicketStatusChanged(
                previousStatus.getValue(),
                status.getValue(),
                this.id,
                modifiedByUUID
        );
        this.recordEvent(event);
    }

    private void changeAssignee(UUID assignee, UUID modifiedByUUID) {

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
        this.recordEvent(ticketAssigneeChanged);
    }
}
