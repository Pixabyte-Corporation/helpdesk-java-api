package com.pixabyte.helpdeskapi.shared.domain;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

public abstract class DomainEvent {

    protected UUID id;
    protected Instant occurredOn;
    protected UUID aggregateId;

    public DomainEvent(UUID aggregateId) {
        this.aggregateId = aggregateId;
        occurredOn = Instant.now();
        id = UUID.randomUUID();
    }

    public UUID getAggregateId() {
        return aggregateId;
    }

    public abstract String getEventName();

    public abstract Map<String, Object> toPrimitives();

}
