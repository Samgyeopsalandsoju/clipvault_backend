package com.samso.linkjoa.infrastructure.mail;

import com.samso.linkjoa.domain.mail.MailSender;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Optional;

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
