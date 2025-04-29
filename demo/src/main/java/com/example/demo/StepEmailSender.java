package com.example.demo;

import org.springframework.stereotype.Component;

@Component("stepEmailSender")
public class StepEmailSender implements EmailSender {

    @Override
    public void send(String to, String message) {
        System.out.printf("smtp 발송 :", to, message);
        
    }
    
}
