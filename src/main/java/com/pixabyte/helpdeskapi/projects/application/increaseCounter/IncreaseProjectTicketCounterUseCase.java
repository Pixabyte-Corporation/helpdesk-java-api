package com.pixabyte.helpdeskapi.projects.application.increaseCounter;

import com.pixabyte.helpdeskapi.authentication.domain.exceptions.UserNotFound;
import com.pixabyte.helpdeskapi.projects.domain.ProjectCounter;
import com.pixabyte.helpdeskapi.projects.domain.ProjectCounterRepository;
import org.springframework.stereotype.Service;

@Service
public class IncreaseProjectTicketCounterUseCase {
    private final ProjectCounterRepository projectCounterRepository;
    public IncreaseProjectTicketCounterUseCase(ProjectCounterRepository projectCounterRepository) {
        this.projectCounterRepository = projectCounterRepository;
    }

    public void execute(IncreaseProjectTicketCounterCommand command) {
        var projectCounterOpt = projectCounterRepository.findById(command.id());
        if (projectCounterOpt.isEmpty()) {
            var newPc = ProjectCounter.create(command.id());
            newPc.addTicket(command.ticketId());
            projectCounterRepository.save(newPc);
            return;
        }
        var projectCounter = projectCounterOpt.get();
        projectCounter.addTicket(command.ticketId());
        projectCounterRepository.save(projectCounter);
    }
}
