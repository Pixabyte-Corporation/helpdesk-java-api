package com.pixabyte.helpdeskapi.tickets.infrastructure.controllers;

import com.pixabyte.helpdeskapi.authentication.infrastructure.security.config.HelpDeskUserDetails;
import com.pixabyte.helpdeskapi.shared.domain.ResultsPage;
import com.pixabyte.helpdeskapi.tickets.application.CreateTicketCommand;
import com.pixabyte.helpdeskapi.tickets.application.CreateTicketUseCase;
import com.pixabyte.helpdeskapi.tickets.application.SearchTicketsUseCase;
import com.pixabyte.helpdeskapi.tickets.domain.TicketPagination;
import com.pixabyte.helpdeskapi.tickets.domain.TicketsFilter;
import com.pixabyte.helpdeskapi.tickets.domain.Ticket;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
public class TicketsController {

    private final CreateTicketUseCase createTicketUseCase;
    private final SearchTicketsUseCase searchTicketsUseCase;

    public TicketsController(CreateTicketUseCase createTicketUseCase, SearchTicketsUseCase searchTicketsUseCase) {
        this.createTicketUseCase = createTicketUseCase;
        this.searchTicketsUseCase = searchTicketsUseCase;
    }

    @PutMapping("/projects/{projectId}/tickets")
    public ResponseEntity<Void> createTicket(
            @PathVariable("projectId") String projectId,
            @RequestBody CreateTicketPutRequest body,
            Authentication authentication
            ) {

        HelpDeskUserDetails helpDeskUserDetails = (HelpDeskUserDetails) authentication.getPrincipal();
        CreateTicketCommand command = new CreateTicketCommand(
                body.id(),
                helpDeskUserDetails.getId(),
                body.assignedTo(),
                UUID.fromString(projectId),
                body.title(),
                body.description(),
                body.status(),
                body.priority()
        );

        createTicketUseCase.execute(command);
        return ResponseEntity.status(200).build();
    }


    @GetMapping("/projects/{projectId}/tickets")
    public ResponseEntity<ResultsPage<Ticket>> getTickets(
            @PathVariable("projectId") String projectId,
            @RequestParam(required = false) Integer priority,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fromDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime toDate,
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "20") Integer pageSize
    ) {
        TicketsFilter filter = new TicketsFilter(
                UUID.fromString(projectId),
                status,
                priority,
                fromDate,
                toDate
        );
        TicketPagination pagination = new TicketPagination(pageSize, pageNumber);

        return ResponseEntity.ok(searchTicketsUseCase.execute(filter, pagination));
    }



}
