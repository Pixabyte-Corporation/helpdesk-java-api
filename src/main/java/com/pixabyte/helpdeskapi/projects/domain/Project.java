package com.pixabyte.helpdeskapi.projects.domain;

import com.pixabyte.helpdeskapi.projects.domain.values.*;
import com.pixabyte.helpdeskapi.shared.domain.AggregateRoot;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class Project extends AggregateRoot {
    private ProjectId id;
    private ProjectName name;
    private ProjectDescription description;
    private OrganizationId organizationId;
    private ProjectOwnerId ownerId;

    private Project(ProjectId id, ProjectName name, ProjectDescription description, OrganizationId organizationId, ProjectOwnerId ownerId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.organizationId = organizationId;
        this.ownerId = ownerId;
    }

    public static Project create(
            ProjectId id,
            ProjectName name,
            ProjectDescription description,
            OrganizationId organizationId,
            ProjectOwnerId ownerId
    ) {
        Project p = new Project(
                id,
                name,
                description,
                organizationId,
                ownerId
        );
        var projectCreated = new ProjectCreatedEvent(
                p.id.value(),
                p.name.value(),
                p.description.value(),
                p.organizationId.value(),
                p.ownerId.value()
        );
        p.recordEvent(projectCreated);
        return p;
    }

    public static Project recreate(
            ProjectId id,
            ProjectName name,
            ProjectDescription description,
            OrganizationId organizationId,
            ProjectOwnerId ownerId) {
        return new Project(
                id,
                name,
                description,
                organizationId,
                ownerId
        );
    }

    public void update(
            ProjectName name,
            ProjectDescription description
    ) {
        this.name = name;
        this.description = description;
        var updatedProjectEvent = new ProjectUpdatedEvent(
                this.id.value(),
                this.name.value(),
                this.description.value());
        recordEvent(updatedProjectEvent);
    }

    public Map<String, Object> toPrimitives() {
        var map = new HashMap<String, Object>();
        map.put("id", id.value());
        map.put("name", name.value());
        map.put("description", description.value());
        map.put("ownerId", ownerId.value());
        map.put("organizationId", organizationId.value());
        return map;
    }

}
