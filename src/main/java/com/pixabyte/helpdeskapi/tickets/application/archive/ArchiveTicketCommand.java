package com.pixabyte.helpdeskapi.tickets.application.archive;

import com.pixabyte.helpdeskapi.shared.domain.values.UserId;
import com.pixabyte.helpdeskapi.tickets.domain.values.TicketId;

public record ArchiveTicketCommand(
        TicketId ticketId,
        UserId archivedBy
) {
}
