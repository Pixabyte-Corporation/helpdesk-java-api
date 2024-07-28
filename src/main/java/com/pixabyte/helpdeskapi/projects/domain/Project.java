package com.pixabyte.helpdeskapi.projects.domain;

import com.pixabyte.helpdeskapi.projects.domain.values.*;
import com.pixabyte.helpdeskapi.shared.domain.AggregateRoot;
import lombok.Getter;

@Getter
public class Project extends AggregateRoot {
    private ProjectId id;
    private ProjectName name;
    private ProjectDescription description;
    private OrganizationId organizationId;
    private ProjectOwnerId ownerId;
    private TicketsCount ticketsCounter;

    private Project(ProjectId id, ProjectName name, ProjectDescription description, OrganizationId organizationId, ProjectOwnerId ownerId, TicketsCount ticketsCounter) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.organizationId = organizationId;
        this.ticketsCounter = ticketsCounter;
        this.ownerId = ownerId;
    }

    public static Project create(
            ProjectId id,
            ProjectName name,
            ProjectDescription description,
            OrganizationId organizationId,
            ProjectOwnerId ownerId
    ) {
        TicketsCount zeroCounter = new TicketsCount(0);
        Project p = new Project(
                id,
                name,
                description,
                organizationId,
                ownerId,
                zeroCounter
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

    public void increaseTicketCounter() {
        ticketsCounter = new TicketsCount(ticketsCounter.value() + 1);
    }


}
