package com.pixabyte.helpdeskapi.tickets.application;

import com.pixabyte.helpdeskapi.shared.domain.EventBus;
import com.pixabyte.helpdeskapi.tickets.application.create.CreateTicketCommand;
import com.pixabyte.helpdeskapi.tickets.application.create.CreateTicketUseCase;
import com.pixabyte.helpdeskapi.tickets.domain.events.TicketCreated;
import com.pixabyte.helpdeskapi.tickets.domain.TicketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.mockito.Mockito.verify;

class CreateTicketUseCaseTest {

    @Mock
    private TicketRepository ticketRepository;
    @Mock
    private EventBus eventBus;
    private CreateTicketUseCase createTicketUseCase;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        createTicketUseCase = new CreateTicketUseCase(ticketRepository, eventBus);
    }

    @Test
    void shouldCreateTicket() {
        CreateTicketCommand command = new CreateTicketCommand(
                UUID.randomUUID(),
                UUID.randomUUID(),
                UUID.randomUUID(),
                UUID.randomUUID(),
                "Uusario no puede iniciar sesión",
                "El usuario tiene error al iniciar sesión",
                "CREATED",
                1
        );
        createTicketUseCase.execute(command);
        verify(ticketRepository).save(ArgumentMatchers.any());
        verify(eventBus).publish(ArgumentMatchers.any(TicketCreated.class));
    }

}