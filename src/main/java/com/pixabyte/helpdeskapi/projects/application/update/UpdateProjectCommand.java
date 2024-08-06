package com.pixabyte.helpdeskapi.projects.application.update;

import com.pixabyte.helpdeskapi.projects.domain.values.ProjectDescription;
import com.pixabyte.helpdeskapi.projects.domain.values.ProjectId;
import com.pixabyte.helpdeskapi.projects.domain.values.ProjectName;

public record UpdateProjectCommand(
        ProjectId id,
        ProjectName name,
        ProjectDescription description
) {
}
