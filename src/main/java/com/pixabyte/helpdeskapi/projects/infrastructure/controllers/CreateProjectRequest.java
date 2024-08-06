package com.pixabyte.helpdeskapi.projects.infrastructure.controllers;

import java.util.UUID;

public record CreateProjectRequest(
        UUID projectId,
        String name,
        String description
) {
}
