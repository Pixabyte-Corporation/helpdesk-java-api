package com.pixabyte.helpdeskapi.projects.infrastructure.controllers;

import com.pixabyte.helpdeskapi.authentication.infrastructure.security.config.HelpDeskUserDetails;
import com.pixabyte.helpdeskapi.projects.application.create.CreateProjectCommand;
import com.pixabyte.helpdeskapi.projects.application.create.CreateProjectUseCase;
import com.pixabyte.helpdeskapi.projects.domain.values.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class CreateProjectPutController {

    private final CreateProjectUseCase createProjectUseCase;


    public CreateProjectPutController(CreateProjectUseCase createProjectUseCase) {
        this.createProjectUseCase = createProjectUseCase;
    }

    @PutMapping("/projects")
    public ResponseEntity<Void> createProject(
            @RequestBody CreateProjectRequest request,
            Authentication authentication
    ) {
        HelpDeskUserDetails userDetails = (HelpDeskUserDetails) authentication.getPrincipal();

        var projectId = Objects.nonNull(request.projectId())?
                request.projectId().toString()
                : "";

        var command = new CreateProjectCommand(
                new ProjectId(projectId),
                new ProjectName(request.name()),
                new ProjectDescription(request.description()),
                new OrganizationId(userDetails.getOrganizationId().toString()),
                new ProjectOwnerId(userDetails.getId().toString())
        );
        createProjectUseCase.execute(command);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
