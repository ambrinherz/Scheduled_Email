package com.ambrin.scheduledemail.service;

import com.ambrin.scheduledemail.model.Config;
import com.ambrin.scheduledemail.model.ConfirmationStatusType;
import com.ambrin.scheduledemail.model.Email;
import com.ambrin.scheduledemail.model.EmailConfirmation;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.function.Consumer;

public class Mailer {

    private static final String SSL_CONNECTION = "javax.net.ssl.SSLSocketFactory";

    private static final String AUTH = "true";

    private static TransportService transportService;

    private static Mailer instance;

    private Config config;

    static {
        transportService = new TransportServiceImpl();
    }

    private Mailer() {

    }

    static void setTransportService(TransportService transService) {
        transportService = transService;
    }

    public static Mailer getInstance(Config config) {
        if (instance == null) {
            instance = new Mailer();
        }
        instance.setConfig(config);
        return instance;
    }

    private void setConfig(Config config) {
        this.config = config;
    }

    public EmailConfirmation sendEmail(Consumer<Email> emailBuilder) {
        Email email = new Email();
        emailBuilder.accept(email);
        return send(email);
    }

    private EmailConfirmation send(Email email) {
        EmailConfirmation confirmation = new EmailConfirmation();
        try {
            Message message = createMessage(
                    email.getTo(),
                    email.getSubject(),
                    email.getBody());

            transportService.send(message);
            confirmation.setStatus(ConfirmationStatusType.SUCCESS);

        } catch (MessagingException e) {
            confirmation.setStatus(ConfirmationStatusType.FAILURE);
        }
        return confirmation;
    }

    private Message createMessage(String to, String subject, String body) throws MessagingException {
        Session session = createSession();
        Message message = new MimeMessage(session);

        message.setFrom(new InternetAddress(config.getFromEmail()));
        message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(to));

        message.setSubject(subject);
        message.setText(body);
        return message;
    }

    private Session createSession() {
        Properties prop = createProperties();

        return Session.getInstance(prop,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(config.getUsername(), config.getPassword());
                    }
                });
    }

    private Properties createProperties() {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", config.getSmtpServer());
        prop.put("mail.smtp.port", config.getPort());
        prop.put("mail.smtp.auth", AUTH);
        prop.put("mail.smtp.socketFactory.PORT", config.getPort());
        prop.put("mail.smtp.socketFactory.class", SSL_CONNECTION);
        return prop;
    }
}


