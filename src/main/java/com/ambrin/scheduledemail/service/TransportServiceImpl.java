package com.ambrin.scheduledemail.service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;

public class TransportServiceImpl implements TransportService {

    @Override
    public void send(Message message) throws MessagingException {
        Transport.send(message);
    }
}
