package com.ambrin.scheduledemail.service;

import javax.mail.Message;
import javax.mail.MessagingException;

public interface TransportService {

    void send(Message message) throws MessagingException;
}
