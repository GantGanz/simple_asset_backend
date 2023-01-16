package com.test.simpleasset.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.test.simpleasset.pojo.EmailPojo;

@Service
public class EmailService{
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public void sendMail(final EmailPojo emailPojo) {
		final SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(emailPojo.getEmail());
		msg.setSubject(emailPojo.getSubject());
		msg.setText(emailPojo.getBody());
		
		javaMailSender.send(msg);
	}
}
