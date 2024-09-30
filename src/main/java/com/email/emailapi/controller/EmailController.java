package com.email.emailapi.controller;

import com.email.emailapi.model.EmailRequest;
import com.email.emailapi.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @RequestMapping("/welcome")
    public String welcom(){
        return "hello! This is my email api.";
    }


    @RequestMapping(value = "/send-email",method= RequestMethod.POST)
    public ResponseEntity<?> sendEmail(@RequestBody EmailRequest emailRequest){
        System.out.println("Done---"+emailRequest.toString());
        boolean result = this.emailService.
                sendEmail(emailRequest.getSubject(),emailRequest.getMessage(),emailRequest.getTo());
        if(result) {
            return ResponseEntity.ok("Send successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email Not Sent!");
        }

    }
}
