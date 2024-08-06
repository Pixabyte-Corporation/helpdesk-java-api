package com.pixabyte.helpdeskapi.projects.domain;

import com.pixabyte.helpdeskapi.projects.domain.values.ProjectId;
import com.pixabyte.helpdeskapi.projects.domain.values.TicketId;
import com.pixabyte.helpdeskapi.shared.domain.AggregateRoot;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
public class ProjectCounter extends AggregateRoot {

    private ProjectId id;
    private List<TicketId> tickets;

    private ProjectCounter(ProjectId id, List<TicketId> tickets) {
        this.id = id;
        this.tickets = tickets;
    }

    public static ProjectCounter create(ProjectId id) {
        return new ProjectCounter(
                id,
                new ArrayList<>()
        );
    }

    public static ProjectCounter recreate(ProjectId id, List<TicketId> tickets) {
        return new ProjectCounter(id, tickets);
    }

    public void addTicket(TicketId ticketId) {
        tickets.add(ticketId);
    }

    public Map<String, Object> toPrimitives() {
        return null;
    }

}
