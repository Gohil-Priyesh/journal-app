package com.priyesh.myFirstProject.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String to, String subject, String body){
        try{
            SimpleMailMessage mail = new SimpleMailMessage();
            ///  setting to , subject and mail in the mail object of SimpleMailMessage
            mail.setFrom("priyeshgohil269@gmail.com");
            mail.setTo(to);
            mail.setSubject(subject);
            mail.setText(body);
            javaMailSender.send(mail); /// and then passing that object to the javaMailSender.send(mail);
        }catch (Exception e){
            log.error("Exception while sendEmail", e);
        }

    }
}
