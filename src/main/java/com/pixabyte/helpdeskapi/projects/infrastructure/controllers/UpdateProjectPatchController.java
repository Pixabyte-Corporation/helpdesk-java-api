package com.pixabyte.helpdeskapi.projects.infrastructure.controllers;

import com.pixabyte.helpdeskapi.projects.application.update.UpdateProjectCommand;
import com.pixabyte.helpdeskapi.projects.application.update.UpdateProjectUseCase;
import com.pixabyte.helpdeskapi.projects.domain.values.ProjectDescription;
import com.pixabyte.helpdeskapi.projects.domain.values.ProjectId;
import com.pixabyte.helpdeskapi.projects.domain.values.ProjectName;
import io.micrometer.observation.annotation.Observed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpdateProjectPatchController {

    private final Logger logger = LoggerFactory.getLogger(UpdateProjectPatchController.class);
    private final UpdateProjectUseCase updateProjectUseCase;

    public UpdateProjectPatchController(UpdateProjectUseCase updateProjectUseCase) {
        this.updateProjectUseCase = updateProjectUseCase;
    }

    @Observed(name = "updateProject-controller")
    @PatchMapping("/projects/{projectId}")
    public ResponseEntity<Void> updateProject(
            @PathVariable("projectId") String projectId,
            @RequestBody UpdateProjectRequest request,
            Authentication authentication
    ) {

        logger.info("UpdateProjectPatchController#updateProject - Starting request for projectId={}",
                projectId);

        ProjectId id = new ProjectId(projectId);
        ProjectName name = new ProjectName(request.name());
        ProjectDescription description = new ProjectDescription(request.description());

        UpdateProjectCommand command = new UpdateProjectCommand(
                id,
                name,
                description
        );

        updateProjectUseCase.execute(command);
        return ResponseEntity.ok().build();
    }

}
