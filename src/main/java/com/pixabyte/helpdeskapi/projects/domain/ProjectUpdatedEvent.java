package com.pixabyte.helpdeskapi.projects.domain;

import com.pixabyte.helpdeskapi.shared.domain.DomainEvent;

import java.util.Map;
import java.util.UUID;

public class ProjectUpdatedEvent extends DomainEvent {

    private final String name;
    private final String description;

    public ProjectUpdatedEvent(String projectId , String name, String description) {
        super(UUID.fromString(projectId));
        this.name = name;
        this.description = description;
    }


    @Override
    public String getEventName() {
        return "projectUpdated";
    }

    @Override
    public Map<String, Object> toPrimitives() {
        return null;
    }
}
