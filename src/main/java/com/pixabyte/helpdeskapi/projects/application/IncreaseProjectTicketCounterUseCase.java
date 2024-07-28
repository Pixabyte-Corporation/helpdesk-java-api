package com.pixabyte.helpdeskapi.projects.application;

import com.pixabyte.helpdeskapi.authentication.domain.UserNotFound;
import com.pixabyte.helpdeskapi.projects.domain.ProjectCounterRepository;

public class IncreaseProjectTicketCounterUseCase {
    private final ProjectCounterRepository projectCounterRepository;
    public IncreaseProjectTicketCounterUseCase(ProjectCounterRepository projectCounterRepository) {
        this.projectCounterRepository = projectCounterRepository;
    }

    public void execute(IncreaseProjectTicketCounterCommand command) {
        var projectCounterOpt = projectCounterRepository.findById(command.id());
        if (projectCounterOpt.isEmpty()) {
            // Exception
            throw new UserNotFound();
        }
        var projectCounter = projectCounterOpt.get();
        projectCounter.addTicket(command.ticketId());
        projectCounterRepository.save(projectCounter);
    }
}
