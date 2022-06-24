package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendMailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public void sendMail(String from, String to, String Subject, String body) {
		
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		
		mailMessage.setFrom(from);
		mailMessage.setTo(to);
		mailMessage.setSubject(Subject);
		mailMessage.setText(body);
		
		javaMailSender.send(mailMessage);
	}
}
