package com.ambrin.scheduledemail.service;

import com.ambrin.scheduledemail.model.Config;
import com.ambrin.scheduledemail.model.ConfirmationStatusType;
import com.ambrin.scheduledemail.model.EmailConfirmation;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;

import javax.mail.MessagingException;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;

public class EmailServiceTest {

    @Mock
    private TransportService transportService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        Mailer.setTransportService(transportService);
    }


    @Test
    public void should_returnSuccess_when_successfullySendEmail() {
        Mailer mailer = Mailer.getInstance(createConfig());
        EmailConfirmation confirmation = mailer.sendEmail(email -> email
                .to("TEST@example.com")
                .body("TEST")
                .subject("hellow"));

        assertEquals(ConfirmationStatusType.SUCCESS, confirmation.getStatus());
    }

    @Test
    public void should_returnFailure_when_sendingEmailToInvalidEmailAddress() throws Exception {
        doThrow(new MessagingException()).when(transportService).send(any());

        Mailer mailer = Mailer.getInstance(createConfig());
        EmailConfirmation confirmation = mailer.sendEmail(email -> email
                .to("TEST")
                .body("TEST")
                .subject("hellow"));

        assertEquals(ConfirmationStatusType.FAILURE, confirmation.getStatus());
    }

    private Config createConfig() {
        Config config = new Config();
        config.setFromEmail("TEST@example.com");
        config.setUsername("TEST_USERNAME");
        config.setPassword("TEST_PASSWORD");
        config.setPort("123");
        config.setSmtpServer("TEST_SERVER");
        return config;
    }


}
