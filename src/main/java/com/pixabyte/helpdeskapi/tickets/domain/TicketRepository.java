package com.pixabyte.helpdeskapi.tickets.domain;

import com.pixabyte.helpdeskapi.shared.domain.ResultsPage;

import java.util.List;

public interface TicketRepository {
    void save(Ticket ticket);
    ResultsPage<Ticket> findAll(TicketsFilter filter, TicketPagination pagination);
}
