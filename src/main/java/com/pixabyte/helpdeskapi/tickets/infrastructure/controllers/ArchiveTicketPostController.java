package com.pixabyte.helpdeskapi.tickets.infrastructure.controllers;

import com.pixabyte.helpdeskapi.authentication.infrastructure.security.config.HelpDeskUserDetails;
import com.pixabyte.helpdeskapi.shared.domain.values.UserId;
import com.pixabyte.helpdeskapi.tickets.application.archive.ArchiveTicketCommand;
import com.pixabyte.helpdeskapi.tickets.application.archive.ArchiveTicketUseCase;
import com.pixabyte.helpdeskapi.tickets.domain.values.TicketId;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArchiveTicketPostController {

    private final ArchiveTicketUseCase archiveTicketUseCase;

    public ArchiveTicketPostController(ArchiveTicketUseCase archiveTicketUseCase) {
        this.archiveTicketUseCase = archiveTicketUseCase;
    }

    @PostMapping("/tickets/{ticketId}")
    public ResponseEntity<Void> archiveTicket(
            @PathVariable("ticketId") String ticketId,
            Authentication authentication
    ) {
        HelpDeskUserDetails ud = (HelpDeskUserDetails) authentication.getPrincipal();
        TicketId ticketIdentifier = new TicketId(ticketId);
        UserId userId = new UserId(ud.getId().toString());
        ArchiveTicketCommand command = new ArchiveTicketCommand(ticketIdentifier, userId);
        archiveTicketUseCase.execute(command);
        return ResponseEntity.ok().build();
    }

}
