package com.pixabyte.helpdeskapi.projects.application;

import com.pixabyte.helpdeskapi.projects.application.create.CreateProjectCommand;
import com.pixabyte.helpdeskapi.projects.application.create.CreateProjectUseCase;
import com.pixabyte.helpdeskapi.projects.domain.Project;
import com.pixabyte.helpdeskapi.projects.domain.ProjectCreatedEvent;
import com.pixabyte.helpdeskapi.projects.domain.ProjectRepository;
import com.pixabyte.helpdeskapi.projects.domain.values.*;
import com.pixabyte.helpdeskapi.shared.domain.EventBus;
import com.pixabyte.helpdeskapi.shared.domain.ParagraphMother;
import com.pixabyte.helpdeskapi.shared.domain.UuidMother;
import com.pixabyte.helpdeskapi.shared.domain.WordMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

class CreateProjectUseCaseTest {

    @Mock
    private ProjectRepository projectRepository;
    @Mock
    private EventBus eventBus;
    private CreateProjectUseCase createProjectUseCase;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        createProjectUseCase = new CreateProjectUseCase(projectRepository, eventBus);
    }

    @Test
    void shouldCreateProject() {
        ProjectId id = new ProjectId(UuidMother.random());
        ProjectName name = new ProjectName(WordMother.random());
        ProjectDescription description = new ProjectDescription(ParagraphMother.random());
        ProjectOwnerId ownerId = new ProjectOwnerId(UuidMother.random());
        OrganizationId organizationId = new OrganizationId(UuidMother.random());
        var command = new CreateProjectCommand(
                id,
                name,
                description,
                organizationId,
                ownerId
        );
        createProjectUseCase.execute(command);
        verify(projectRepository).save(any(Project.class));
        verify(eventBus).publish(any(ProjectCreatedEvent.class));
    }

}