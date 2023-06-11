package com.fees.management.app.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService {

    @Autowired
    JavaMailSender javaMailSender;

    public void sendEmail(String toEmail, String mailBody, String subject){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("springemailsender998@gmail.com");
        simpleMailMessage.setTo(toEmail);
        simpleMailMessage.setText(mailBody);
        simpleMailMessage.setSubject(subject);

        javaMailSender.send(simpleMailMessage);
        log.info("Mail Sent Successfully......" + simpleMailMessage);
    }
}
