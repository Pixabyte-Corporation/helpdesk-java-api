package com.pixabyte.helpdeskapi.shared.infrastructure.bus;

import com.pixabyte.helpdeskapi.shared.domain.DomainEvent;
import com.pixabyte.helpdeskapi.shared.domain.EventBus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class SpringEventBus implements EventBus {

    private final Logger logger = LoggerFactory.getLogger(SpringEventBus.class);

    private final ApplicationEventPublisher eventPublisher;

    public SpringEventBus(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void publish(DomainEvent event) {
        logger.info("SpringEventBus#publish - Event has been published type={}", event.getEventName());
        eventPublisher.publishEvent(event);
    }
}
