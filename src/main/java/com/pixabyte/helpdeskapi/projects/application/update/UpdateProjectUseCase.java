package com.pixabyte.helpdeskapi.projects.application.update;

import com.pixabyte.helpdeskapi.projects.domain.Project;
import com.pixabyte.helpdeskapi.projects.domain.ProjectNotFound;
import com.pixabyte.helpdeskapi.projects.domain.ProjectRepository;
import com.pixabyte.helpdeskapi.shared.domain.EventBus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateProjectUseCase {

    private final ProjectRepository projectRepository;
    private final EventBus eventBus;
    private final Logger logger = LoggerFactory.getLogger(UpdateProjectUseCase.class);

    public UpdateProjectUseCase(ProjectRepository projectRepository, EventBus eventBus) {
        this.projectRepository = projectRepository;
        this.eventBus = eventBus;
    }

    public void execute(UpdateProjectCommand command) {

        Optional<Project> projectOpt = projectRepository.findById(command.id());

        if (projectOpt.isEmpty()) {
            logger.error("UpdateProjectUseCase#execute - Project not found with id={}",
                    command.id().toString());
            throw new ProjectNotFound();
        }
        logger.info("UpdateProjectUseCase#execute - Project found id={}", command.id().value());
        var project = projectOpt.get();
        project.update(command.name(), command.description());
        projectRepository.save(project);
        logger.info("UpdateProjectUseCase#execute - Project updated successfully projectId={} name={}",
                command.id().value(),
                command.name().value());
        project.pullEvents().forEach(eventBus::publish);
    }


}
