package com.fullcrudops.crudform.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class ElasticMailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public void sendConfirmEmail(String toEmail, String userName) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(toEmail);
		message.setSubject("User Registration confirmation");
		message.setText("Hello "+userName+",\n\nYour registration was successful.\n\nThank You.");
		
		try {
            mailSender.send(message);
            System.out.println("Email sent successfully to " + toEmail);
        } catch (Exception e) {
            System.out.println("Failed to send email: " + e.getMessage());
        }
		
	}

}
