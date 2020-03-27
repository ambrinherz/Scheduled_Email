package com.ambrin.scheduledemail.scheduler;

import com.ambrin.scheduledemail.model.EmailConfirmation;
import com.ambrin.scheduledemail.service.MailerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PillScheduledTask {

    private static final Logger log = LoggerFactory.getLogger(PillScheduledTask.class);

    private MailerService mailerService;

    @Value("${scheduledemail.toemail}")
    private String toEmail;

    @Autowired
    public PillScheduledTask(MailerService mailerService) {
        this.mailerService = mailerService;
    }

    @Scheduled(cron = "0 0 10 * * ?")
    public void sendEmail() {
        EmailConfirmation confirmation = mailerService.sendEmail(email -> email
                .to(toEmail)
                .subject("BC pill reminder")
                .body("Hello. It's 10 am and it's time to take your BC pill now."));

        log.info("Task ran with status: {}", confirmation.getStatus());
    }
}
