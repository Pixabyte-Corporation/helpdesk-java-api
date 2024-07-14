package com.pixabyte.helpdeskapi.tickets.application;

import com.pixabyte.helpdeskapi.shared.domain.ResultsPage;
import com.pixabyte.helpdeskapi.tickets.domain.Ticket;
import com.pixabyte.helpdeskapi.tickets.domain.TicketPagination;
import com.pixabyte.helpdeskapi.tickets.domain.TicketRepository;
import com.pixabyte.helpdeskapi.tickets.domain.TicketsFilter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchTicketsUseCase {

    private final TicketRepository ticketRepository;

    public SearchTicketsUseCase(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public ResultsPage<Ticket> execute(TicketsFilter filter, TicketPagination pagination) {
        return ticketRepository.findAll(filter, pagination);
    }

}
