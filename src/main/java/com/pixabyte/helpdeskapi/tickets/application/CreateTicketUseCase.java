package com.pixabyte.helpdeskapi.tickets.application;

import com.pixabyte.helpdeskapi.tickets.domain.Ticket;
import com.pixabyte.helpdeskapi.tickets.domain.TicketRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateTicketUseCase {

    private final TicketRepository ticketRepository;

    public CreateTicketUseCase(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public void execute(CreateTicketCommand command) {
        Ticket ticket = Ticket.builder()
                .id(command.ticketId())
                .title(command.title())
                .description(command.description())
                .status(command.status())
                .priority(command.priority())
                .projectId(command.projectId())
                .reporterId(command.reporterId())
                .assignedToId(command.assignedTo())
                .build();
        ticketRepository.save(ticket);
    }

}
