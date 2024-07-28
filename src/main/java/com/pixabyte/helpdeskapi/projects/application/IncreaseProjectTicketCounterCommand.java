package com.pixabyte.helpdeskapi.projects.application;

import com.pixabyte.helpdeskapi.projects.domain.values.ProjectId;
import com.pixabyte.helpdeskapi.projects.domain.values.TicketId;

public record IncreaseProjectTicketCounterCommand(
        ProjectId id,
        TicketId ticketId
) {
}
