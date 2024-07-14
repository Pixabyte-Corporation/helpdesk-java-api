package com.pixabyte.helpdeskapi.notification.infrastructure;

import com.pixabyte.helpdeskapi.authentication.domain.UserRegisteredEvent;
import com.pixabyte.helpdeskapi.notification.application.SendVerificationEmailCommand;
import com.pixabyte.helpdeskapi.notification.application.SendVerificationEmailUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationEventHandler {

    private final Logger logger = LoggerFactory.getLogger(NotificationEventHandler.class);
    private final SendVerificationEmailUseCase sendVerificationEmailUseCase;

    public NotificationEventHandler(SendVerificationEmailUseCase sendVerificationEmailUseCase) {
        this.sendVerificationEmailUseCase = sendVerificationEmailUseCase;
    }

    @EventListener
    public void handleUserRegistered(UserRegisteredEvent event) {
        logger.info("NotificationEventHandler#handleUserRegistered - Event received");
        var command = new SendVerificationEmailCommand(
                event.getAggregateId(),
                event.getEmail(),
                event.getName()
        );
        sendVerificationEmailUseCase.execute(command);
    }

}
