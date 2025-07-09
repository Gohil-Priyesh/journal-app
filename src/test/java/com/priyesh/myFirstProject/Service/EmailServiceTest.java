package com.priyesh.myFirstProject.Service;

import com.priyesh.myFirstProject.service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {

    @Autowired
    EmailService emailService;

    @Test
     void sendEmailTest(){
        emailService.sendEmail("priyeshgohil269@gmail.com","firstMail hi","kem cho bhai");
    }
}
