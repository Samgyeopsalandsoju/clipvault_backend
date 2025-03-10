package com.samso.linkjoa.core.infrastructure.mail;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GmailMailSender implements MailSender {

    private final JavaMailSender mailSender;
    @Override
    public boolean sendMail(String to, String subject, String body) {
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);
            mailSender.send(message);

            return true;
        } catch(Exception e){
            return false;
        }
    }
}
