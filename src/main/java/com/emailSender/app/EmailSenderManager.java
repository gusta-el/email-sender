/*
 * Created in 03-13-2020
 * 
 */
package com.emailSender.app;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.emailSender.app.model.EmailContent;
import com.emailSender.app.model.EmailProperties;

/**
 * The class to send emails
 *
 * @author Gustavo Cabrera Cirilo
 * @since   1.0
 */
public class EmailSenderManager 
{
	
	public static final String AUTH = "true";
	public static final String SMTP_ENABLE = "true";
		
    /**
     * Get and set SMTP server properties, create a session, message and send it.
     *
     * @param      EmailSender   data to send emails - properties.
     * 			   EmailContent  email content (toAdress, subject and message).
     * @exception  IOException  if an error occurred when reading from the
     *             input stream.
     * @throws     AddressException
     *             MessagingException
     * @throws     NullPointerException if {@code EmailSender} is null.
     * @since 1.0
     */
    public void send(EmailProperties emailProperties, EmailContent content) throws AddressException,
            MessagingException {

        // sets SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", emailProperties.getHost());
        properties.put("mail.smtp.port", emailProperties.getPort());
        properties.put("mail.smtp.auth", AUTH);
        properties.put("mail.smtp.starttls.enable", SMTP_ENABLE);
        properties.put("mail.smtp.user", emailProperties.getFromAdress());

        // creates a new session, no Authenticator (will connect() later)
        Session session = Session.getDefaultInstance(properties);

        // creates a new e-mail message
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(emailProperties.getFromAdress()));        
		msg.setSubject(content.getSubject());
		msg.setSentDate(new Date());
		msg.setText(content.getMessage());

        if(Objects.nonNull(content.getToAddressesTo()))
        	this.setRecipients(msg, Message.RecipientType.TO, content.getToAddressesTo());
        if(Objects.nonNull(content.getToAddressesCc()))
        	this.setRecipients(msg, Message.RecipientType.CC, content.getToAddressesCc());
        if(Objects.nonNull(content.getToAddressesCo()))
        	this.setRecipients(msg, Message.RecipientType.BCC, content.getToAddressesCo());

        // sends the e-mail
        Transport t = session.getTransport("smtp");
        t.connect(emailProperties.getFromAdress(), emailProperties.getFromAdressPassword());
        t.sendMessage(msg, msg.getAllRecipients());
        t.close();
        
        System.out.println( "============Emails sent with success============");

    }
    
	private void setRecipients(Message msg, RecipientType recipientType, List<String> adressess)
			throws MessagingException {
	
		InternetAddress[] intenertAdressess = new InternetAddress[adressess.size()];

		for (int i = 0; i < intenertAdressess.length; i++) {
			intenertAdressess[i] = new InternetAddress(adressess.get(i));
		}

		msg.setRecipients(recipientType, intenertAdressess);
	}
    
}
