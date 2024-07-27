package com.pixabyte.helpdeskapi.tickets.application;

import com.pixabyte.helpdeskapi.shared.domain.EventBus;
import com.pixabyte.helpdeskapi.tickets.domain.Ticket;
import com.pixabyte.helpdeskapi.tickets.domain.TicketRepository;
import com.pixabyte.helpdeskapi.tickets.domain.TicketStatus;
import org.springframework.stereotype.Service;

import static com.pixabyte.helpdeskapi.tickets.domain.Ticket.createTicket;

@Service
public class CreateTicketUseCase {

    private final TicketRepository ticketRepository;
    private final EventBus eventBus;

    public CreateTicketUseCase(TicketRepository ticketRepository, EventBus eventBus) {
        this.ticketRepository = ticketRepository;
        this.eventBus = eventBus;
    }

    public void execute(CreateTicketCommand command) {
        Ticket ticket = createTicket(
                command.ticketId(),
                command.title(),
                command.description(),
                command.priority(),
                TicketStatus.OPEN,
                command.reporterId(),
                command.assignedTo(),
                command.projectId()
        );
        ticketRepository.save(ticket);
        ticket.pullEvents().forEach(eventBus::publish);
    }

}



/**
 * 1. Completar el módulo de tickets
 * 2. Guardar los eventos de dominio en base de datos
 * 3. Comenzar con el módulo de comentarios
 * 4.
 * */
