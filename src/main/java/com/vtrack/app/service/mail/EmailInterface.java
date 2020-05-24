package com.vtrack.app.service.mail;

import java.io.File;

public interface EmailInterface {

	public boolean sendPlainEmail(String emailId, String content);
	public boolean sendAttachmentEmail(String emailId, String content, File file);
	
}