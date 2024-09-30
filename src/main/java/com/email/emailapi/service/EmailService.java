package com.email.emailapi.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.mail.*; // Change from javax to jakarta
import jakarta.mail.internet.InternetAddress; // Change from javax to jakarta
import jakarta.mail.internet.MimeMessage; // Change from javax to jakarta
import java.util.Properties;

@Service
public class EmailService {

    @Value("${spring.mail.username}")
    private String from; // Your email (loaded from application properties)

    @Value("${spring.mail.password}")
    private String password; // Your email password (loaded from application properties)

    public boolean sendEmail(String subject, String messageBody, String to) {
        boolean sent = false;

        // Setting up mail server properties
        String host = "smtp.gmail.com";
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Create a session object, passing in the properties and a new Authenticator
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        session.setDebug(true); // For debug purposes

        try {
            // Creating a default MimeMessage object
            MimeMessage message = new MimeMessage(session);

            // Setting From: header field
            message.setFrom(new InternetAddress(from));

            // Setting To: header field
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Setting Subject: header field
            message.setSubject(subject);

            // Setting the actual message
            message.setText(messageBody);

            // Sending the email
            Transport.send(message);
            sent = true;

            System.out.println("Email sent successfully!");

        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
        return sent;
    }
}
