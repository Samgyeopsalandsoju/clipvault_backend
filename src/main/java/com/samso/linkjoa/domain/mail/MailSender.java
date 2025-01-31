package com.samso.linkjoa.domain.mail;

import java.util.Optional;

public interface MailSender {
    public boolean sendMail(String to, String subject, String body);

}
