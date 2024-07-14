package com.pixabyte.helpdeskapi.notification.infrastructure;

import com.pixabyte.helpdeskapi.notification.domain.EmailContent;
import com.pixabyte.helpdeskapi.notification.domain.EmailProperties;
import com.pixabyte.helpdeskapi.notification.domain.EmailSender;
import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.emails.model.CreateEmailResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ResendEmailSender implements EmailSender {

    private final String resendApiKey;
    private final String verifyBaseUrl;

    public ResendEmailSender(
            @Value("${helpdesk.notifications.email.api-key}") String resendApiKey,
            @Value("${helpdesk.notifications.verify-mail.base.url}") String verifyBaseUrl) {
        this.resendApiKey = resendApiKey;
        this.verifyBaseUrl = verifyBaseUrl;
    }

    @Override
    public void send(EmailProperties emailProperties, EmailContent content) {
        Resend client = new Resend(resendApiKey);
        StringBuilder htmlContent = new StringBuilder();
        htmlContent.append("<strong>Bienvenido " + content.getUserFullName() + "</strong><br />");
        htmlContent.append("<p>"+ content.getMessage() +"</p>");
        htmlContent.append("<h3>Verifica tu correo</h3>");
        htmlContent.append("<a href=\"" + this.verifyBaseUrl + emailProperties.getUserId().toString() +"\">Da clic aqui para verificar</a>");

        CreateEmailOptions params = CreateEmailOptions.builder()
                .from("Acme <onboarding@resend.dev>")
                .to(emailProperties.getToEmail())
                .subject(emailProperties.getSubject())
                .html(htmlContent.toString())
                .build();

        try {
            CreateEmailResponse data = client.emails().send(params);
        } catch (ResendException e) {
            e.printStackTrace();
        }
    }
}
