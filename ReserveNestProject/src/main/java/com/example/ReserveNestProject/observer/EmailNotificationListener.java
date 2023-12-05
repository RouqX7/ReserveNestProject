package com.example.ReserveNestProject.observer;

import org.springframework.stereotype.Component;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;


@Component
public class EmailNotificationListener implements PropertyChangeListener {
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String newValue = evt.getNewValue().toString();
        String[] parts = newValue.split(",");
        if (parts.length > 1 && "status".equals(evt.getPropertyName())) {
            String newStatus = parts[0];
            String customerEmail = parts[1];
            sendEmailNotification(newStatus, customerEmail);
        }
    }


    public void sendEmailNotification(String newStatus, String to) {
        // Defining recipient, sender, host,
        String from = "farouq2169@gmail.com";
        String host = "smtp.gmail.com";
        // Get system properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        // Setup mail server
        properties.setProperty("smtp.gmail.com", host);

        // Get the default Session object
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, "your-password");
            }
        });
        try {
            // Create a default MimeMessage object
            MimeMessage message = new MimeMessage(session);
            // Set From: header field
            message.setFrom(new InternetAddress(from));
            // Set To: header field
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            // Set Subject: header field
            message.setSubject("Your Booking Status has been updated!");
            // Set the actual message
            message.setText("Dear Customer,\n\nYour booking status has been updated to: " + newStatus);
            // Send message
            Transport.send(message);
            System.out.println("Sent email notification successfully...");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }

}
