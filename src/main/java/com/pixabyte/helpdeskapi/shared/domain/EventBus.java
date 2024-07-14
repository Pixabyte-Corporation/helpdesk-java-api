package com.pixabyte.helpdeskapi.shared.domain;

public interface EventBus {
    void publish(DomainEvent event);

}
