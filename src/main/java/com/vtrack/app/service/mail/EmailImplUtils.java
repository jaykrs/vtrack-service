package com.vtrack.app.service.mail;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import com.mailjet.client.ClientOptions;
import com.mailjet.client.resource.Emailv31;
import org.json.JSONArray;
import org.json.JSONObject;

import com.vtrack.app.service.constant.ServiceConstants;
@Component
public class EmailImplUtils implements EmailInterface{

	@Autowired
    private JavaMailSender javaMailSender;
	
	@Autowired
	private Environment env;
	
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

	public void sendMailJetEmail(String subject, String body, String toEmail, String toName ) {
		 MailjetClient client;
		    MailjetRequest request;
		    MailjetResponse response = null;
		    client = new MailjetClient(env.getProperty("mail.send.apikey"), env.getProperty("mail.send.apisecret"), new ClientOptions("v3.1"));
		    request = new MailjetRequest(Emailv31.resource)
		    .property(Emailv31.MESSAGES, new JSONArray()
		    .put(new JSONObject()
		    .put(Emailv31.Message.FROM, new JSONObject()
		    .put("Email", env.getProperty("mail.send.fromEmail"))
		    .put("Name", env.getProperty("mail.send.fromName")))
		    .put(Emailv31.Message.TO, new JSONArray()
		    .put(new JSONObject()
		    .put("Email", toEmail)
		    .put("Name", toName)))
		    .put(Emailv31.Message.SUBJECT, subject)
		    .put(Emailv31.Message.TEXTPART, env.getProperty("mail.send.vname"))
		    .put(Emailv31.Message.HTMLPART, body)
		    .put(Emailv31.Message.CUSTOMID, toEmail)));
		    try {
				response = client.post(request);
			} catch (MailjetException | MailjetSocketTimeoutException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    System.out.println(response.getStatus());
	}
	
	@Override
	public boolean sendAttachmentEmail(String emailId, String content, File file) {
		// TODO Auto-generated method stub
		return false;
	}

}
