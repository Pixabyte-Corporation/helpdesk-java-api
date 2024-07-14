package com.pixabyte.helpdeskapi.notification.domain;

public interface EmailSender {

    void send(EmailProperties emailProperties, EmailContent content);

}
