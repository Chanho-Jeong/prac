package com.example.demo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final EmailSender emailSender;

    public UserService(@Qualifier("stepEmailSender") EmailSender emailSender){
        this.emailSender = emailSender;
    }

    public void join(String user) { 
        emailSender.send(user, "가입환영");

        
    }

    public void withdraw(String user) {
        emailSender.send(user, "탈퇴 안내 메일입니다.");
    }
    
}
