package com.pixabyte.helpdeskapi.tickets.application;

import com.pixabyte.helpdeskapi.shared.domain.EventBus;
import com.pixabyte.helpdeskapi.tickets.domain.Ticket;
import com.pixabyte.helpdeskapi.tickets.domain.TicketNotFound;
import com.pixabyte.helpdeskapi.tickets.domain.TicketRepository;
import com.pixabyte.helpdeskapi.tickets.domain.TicketStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateTicketUseCase {

    private final TicketRepository ticketRepository;
    private final EventBus eventBus;
    public UpdateTicketUseCase(TicketRepository ticketRepository, EventBus eventBus) {
        this.ticketRepository = ticketRepository;
        this.eventBus = eventBus;
    }

    /**
     * 1. Buscar el ticket
     * 2. Actualizarlo
     * 3. Emitir eventos de dominio
     * */
    public void execute(UpdateTicketCommand command) {


        Optional<Ticket> ticketOpt = ticketRepository.findById(command.ticketId());
        if (ticketOpt.isEmpty()) {
            throw new TicketNotFound();
        }

        Ticket ticket = ticketOpt.get();
        TicketStatus status = TicketStatus.of(command.status());
        ticket.update(
                command.title(),
                command.description(),
                status,
                command.assignedTo(),
                command.priority(),
                command.mmodifiedByUUID()
        );
        ticketRepository.save(ticket);
        ticket.pullEvents().forEach(eventBus::publish);
    }

}
