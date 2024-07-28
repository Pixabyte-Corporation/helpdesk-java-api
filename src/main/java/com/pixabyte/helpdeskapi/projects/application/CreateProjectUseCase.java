package com.pixabyte.helpdeskapi.projects.application;

import com.pixabyte.helpdeskapi.projects.domain.Project;
import com.pixabyte.helpdeskapi.projects.domain.ProjectRepository;
import com.pixabyte.helpdeskapi.shared.domain.EventBus;

public class CreateProjectUseCase {

    private final ProjectRepository projectRepository;
    private final EventBus eventBus;

    public CreateProjectUseCase(ProjectRepository projectRepository, EventBus eventBus) {
        this.projectRepository = projectRepository;
        this.eventBus = eventBus;
    }

    public void execute(CreateProjectCommand command) {
        Project project = Project.create(
                command.id(),
                command.name(),
                command.description(),
                command.organizationId(),
                command.ownerId()
        );
        projectRepository.save(project);
        project.pullEvents().forEach(eventBus::publish);
    }

}
