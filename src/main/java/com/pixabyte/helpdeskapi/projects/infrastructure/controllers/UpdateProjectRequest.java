package com.pixabyte.helpdeskapi.projects.infrastructure.controllers;

public record UpdateProjectRequest(
        String projectId,
        String name,
        String description
) {
}
