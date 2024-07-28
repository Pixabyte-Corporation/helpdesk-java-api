package com.pixabyte.helpdeskapi.projects.domain;

import com.pixabyte.helpdeskapi.shared.domain.DomainEvent;

import java.util.Map;
import java.util.UUID;

public class ProjectCreatedEvent extends DomainEvent {

    private final String name;
    private final String description;
    private final String organizationId;
    private final String createdByUserId;

    public ProjectCreatedEvent(String projectId ,String name, String description, String organizationId, String createdByUserId) {
        super(UUID.fromString(projectId));
        this.name = name;
        this.description = description;
        this.organizationId = organizationId;
        this.createdByUserId = createdByUserId;
    }


    @Override
    public String getEventName() {
        return "projectCreated";
    }

    @Override
    public Map<String, Object> toPrimitives() {
        return null;
    }
}
