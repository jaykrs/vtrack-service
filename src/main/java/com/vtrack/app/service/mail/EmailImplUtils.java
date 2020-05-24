package com.vtrack.app.service.mail;

import java.io.File;

import org.springframework.stereotype.Component;
@Component
public class EmailImplUtils implements EmailInterface{

	public EmailImplUtils() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean sendPlainEmail(String emailId, String content) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean sendAttachmentEmail(String emailId, String content, File file) {
		// TODO Auto-generated method stub
		return false;
	}

}
