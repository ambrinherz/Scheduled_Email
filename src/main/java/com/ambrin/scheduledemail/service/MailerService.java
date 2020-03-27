package com.ambrin.scheduledemail.service;

import com.ambrin.scheduledemail.model.Email;
import com.ambrin.scheduledemail.model.EmailConfirmation;

import java.util.function.Consumer;

public interface MailerService {

    EmailConfirmation sendEmail(Consumer<Email> emailBuilder);
}


