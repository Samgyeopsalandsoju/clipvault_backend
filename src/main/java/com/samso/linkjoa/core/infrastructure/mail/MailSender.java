package com.samso.linkjoa.core.infrastructure.mail;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface MailSender {
    public boolean sendMail(String to, String subject, String body);

}
