package com.pixabyte.helpdeskapi.tickets.application.archive;

import com.pixabyte.helpdeskapi.shared.domain.EventBus;
import com.pixabyte.helpdeskapi.tickets.domain.Ticket;
import com.pixabyte.helpdeskapi.tickets.domain.TicketNotFound;
import com.pixabyte.helpdeskapi.tickets.domain.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ArchiveTicketUseCase {

    private final TicketRepository ticketRepository;
    private final EventBus eventBus;

    public ArchiveTicketUseCase(TicketRepository ticketRepository, EventBus eventBus) {
        this.ticketRepository = ticketRepository;
        this.eventBus = eventBus;
    }

    public void execute(ArchiveTicketCommand command) {
        Optional<Ticket> ticketOpt = ticketRepository.findById(UUID.fromString(command.ticketId().toString()));
        if (ticketOpt.isEmpty()) {
            throw new TicketNotFound();
        }

        var ticket = ticketOpt.get();
        ticket.archive(command.archivedBy());
        ticketRepository.save(ticket);
        ticket.pullEvents().forEach(eventBus::publish);
    }

}
