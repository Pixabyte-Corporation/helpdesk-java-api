package com.pixabyte.helpdeskapi.tickets.application;

import com.pixabyte.helpdeskapi.shared.domain.EventBus;
import com.pixabyte.helpdeskapi.tickets.domain.Ticket;
import com.pixabyte.helpdeskapi.tickets.domain.TicketNotFound;
import com.pixabyte.helpdeskapi.tickets.domain.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UpdateTicketUseCase {

    private final TicketRepository ticketRepository;
    private final EventBus eventBus;
    public UpdateTicketUseCase(TicketRepository ticketRepository, EventBus eventBus) {
        this.ticketRepository = ticketRepository;
        this.eventBus = eventBus;
    }

    public void execute(UpdateTicketCommand command) {
        /**
         * 1. Buscar el ticket
         * 2. Actualizarlo
         * 3. Emitir eventos de dominio
        * */

        Optional<Ticket> ticketOpt = ticketRepository.findById(command.ticketId());
        if (ticketOpt.isEmpty()) {
            throw new TicketNotFound();
        }

        Ticket ticket = ticketOpt.get();
        ticket.changeAssignee(command.assignedTo(), command.mmodifiedByUUID());
        ticket.changeStatus(command.status(), command.mmodifiedByUUID());

        /**
         * 1. request el assignee es nulo y en la base no lo es
         * 2. request el assignee tiene valor y en la base no
         * 3. base el assignee es nulo y el request no
         * 4. base el assignee tiene valo y el request no
         * */


        if (!command.assignedTo().equals(ticket.getAssignedToId())) {
            ticket.changeAssignee(
                    command.assignedTo(),
                    command.mmodifiedByUUID());
        }

        ticketRepository.save(ticket);
        ticket.pullEvents().forEach(eventBus::publish);

    }

}
