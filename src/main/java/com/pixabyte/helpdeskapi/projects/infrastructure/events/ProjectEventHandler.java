package com.pixabyte.helpdeskapi.projects.infrastructure.events;

import com.pixabyte.helpdeskapi.projects.application.increaseCounter.IncreaseProjectTicketCounterCommand;
import com.pixabyte.helpdeskapi.projects.application.increaseCounter.IncreaseProjectTicketCounterUseCase;
import com.pixabyte.helpdeskapi.projects.domain.values.ProjectId;
import com.pixabyte.helpdeskapi.projects.domain.values.TicketId;
import com.pixabyte.helpdeskapi.tickets.domain.events.TicketCreated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ProjectEventHandler {
    private final Logger logger = LoggerFactory.getLogger(ProjectEventHandler.class);

    private final IncreaseProjectTicketCounterUseCase increaseProjectTicketCounterUseCase;

    public ProjectEventHandler(IncreaseProjectTicketCounterUseCase increaseProjectTicketCounterUseCase) {
        this.increaseProjectTicketCounterUseCase = increaseProjectTicketCounterUseCase;
    }

    @Async
    @EventListener
    public void handleTicketCreated(TicketCreated e) {
        logger.info("ProjectEventHandler#handleTicketCreated - Event received");
        var command = new IncreaseProjectTicketCounterCommand(
                new ProjectId(e.getProjectId().toString()),
                new TicketId(e.getAggregateId().toString())
        );
        increaseProjectTicketCounterUseCase.execute(command);
    }

}
