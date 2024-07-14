package com.pixabyte.helpdeskapi.authentication.domain;

import com.pixabyte.helpdeskapi.shared.domain.DomainEvent;
import lombok.Getter;

import java.util.Map;
import java.util.UUID;

@Getter
public class UserRegisteredEvent extends DomainEvent {

    private final String EVENT_NAME = "userRegistered";

    private final String name;
    private final String email;
    private final UUID organizationId;
    private final UUID roleId;


    public UserRegisteredEvent(UUID aggregateId, String name, String email, UUID organizationId, UUID roleId) {
        super(aggregateId);
        this.name = name;
        this.email = email;
        this.organizationId = organizationId;
        this.roleId = roleId;
    }

    @Override
    public String getEventName() {
        return EVENT_NAME;
    }

    @Override
    public Map<String, Object> toPrimitives() {
        return null;
    }
}
