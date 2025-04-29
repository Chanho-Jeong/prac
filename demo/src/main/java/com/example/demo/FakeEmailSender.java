package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class FakeEmailSender implements EmailSender{

    @Override
    public void send(String to, String message) {
        
        System.out.printf("Fake 메일 :", to, message);
        
    }
    
}
