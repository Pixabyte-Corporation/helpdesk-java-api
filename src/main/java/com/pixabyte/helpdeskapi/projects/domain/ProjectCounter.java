package com.pixabyte.helpdeskapi.projects.domain;

import com.pixabyte.helpdeskapi.projects.domain.values.ProjectId;
import com.pixabyte.helpdeskapi.projects.domain.values.TicketId;
import com.pixabyte.helpdeskapi.shared.domain.AggregateRoot;

import java.util.List;

public class ProjectCounter extends AggregateRoot {

    private ProjectId id;
    private List<TicketId> tickets;

    public void addTicket(TicketId ticketId) {
        tickets.add(ticketId);
    }


}
