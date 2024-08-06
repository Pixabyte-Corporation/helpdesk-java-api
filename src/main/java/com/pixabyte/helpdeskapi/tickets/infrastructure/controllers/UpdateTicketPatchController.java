package com.pixabyte.helpdeskapi.tickets.infrastructure.controllers;

import com.pixabyte.helpdeskapi.authentication.infrastructure.security.config.HelpDeskUserDetails;
import com.pixabyte.helpdeskapi.tickets.application.update.UpdateTicketCommand;
import com.pixabyte.helpdeskapi.tickets.application.update.UpdateTicketUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class UpdateTicketPatchController {

    private final UpdateTicketUseCase updateTicketUseCase;

    public UpdateTicketPatchController(UpdateTicketUseCase updateTicketUseCase) {
        this.updateTicketUseCase = updateTicketUseCase;
    }

    @PatchMapping("/projects/{projectId}/tickets/{ticketId}")
    public ResponseEntity<Void> updateTicket(
            @PathVariable("projectId") String projectId,
            @PathVariable("ticketId") String ticketId,
            @RequestBody UpdateTicketPatchRequest request,
            Authentication authentication
    ) {
        HelpDeskUserDetails userDetails = (HelpDeskUserDetails) authentication.getPrincipal();
        UpdateTicketCommand command = new UpdateTicketCommand(
                UUID.fromString(ticketId),
                request.assigneeId(),
                request.title(),
                request.description(),
                request.status(),
                request.priority(),
                userDetails.getId()
        );
        updateTicketUseCase.execute(command);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
