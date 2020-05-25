package com.vtrack.app.service.mail;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.vtrack.app.service.constant.ServiceConstants;
@Component
public class EmailImplUtils implements EmailInterface{

	@Autowired
    private JavaMailSender javaMailSender;
	
	public EmailImplUtils() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void sendPlainEmail(String emailId, String content) {
		SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(emailId);

        msg.setSubject("Mail from vtrack");
        msg.setText(content);
        msg.setFrom(ServiceConstants.MAILFROM);
        javaMailSender.send(msg);
	}

	@Override
	public boolean sendAttachmentEmail(String emailId, String content, File file) {
		// TODO Auto-generated method stub
		return false;
	}

}
