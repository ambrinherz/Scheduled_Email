package com.ambrin.scheduledemail.service;

import com.ambrin.scheduledemail.model.Config;
import com.ambrin.scheduledemail.model.Email;
import com.ambrin.scheduledemail.model.EmailConfirmation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
public class MailerServiceImpl implements MailerService {

    @Value("${scheduledemail.fromemail}")
    private String FROM_EMAIL;

    @Value("${scheduledemail.username}")
    private String USERNAME;

    @Value("${scheduledemail.password}")
    private String PASSWORD;

    @Value("${scheduledemail.smtpserver}")
    private String SMTP_SERVER;

    @Value("${scheduledemail.port}")
    private String PORT;

    private Config config;

    @Override
    public EmailConfirmation sendEmail(Consumer<Email> emailBuilder) {
        return Mailer.getInstance(getConfig())
                .sendEmail(emailBuilder);
    }

    private Config createConfig() {
        Config config = new Config();
        config.setFromEmail(FROM_EMAIL);
        config.setUsername(USERNAME);
        config.setPassword(PASSWORD);
        config.setSmtpServer(SMTP_SERVER);
        config.setPort(PORT);
        return config;
    }

    private Config getConfig() {
        if (config == null) {
            config = createConfig();
        }
        return config;
    }


}
