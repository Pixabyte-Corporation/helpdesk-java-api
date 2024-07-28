package com.pixabyte.helpdeskapi.projects.application;

import com.pixabyte.helpdeskapi.projects.domain.values.*;

public record CreateProjectCommand(
        ProjectId id,
        ProjectName name,
        ProjectDescription description,
        OrganizationId organizationId,
        ProjectOwnerId ownerId
) {
}
