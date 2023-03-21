package com.kumarsusant.utils;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class EmailUtil {
	private static final Logger logger = LoggerFactory.getLogger(EmailUtil.class);
	private static String username = "noreplybcare@gmail.com";
	private static String password = "fdrqoophalgyfvnd";
	private static String smtpHost = "smtp.gmail.com";

	private static Session session = null;

	private static Session getMailSession() {
		if (session == null) {
			try {
				Properties props = new Properties();
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.starttls.enable", "true");
				props.put("mail.smtp.port", "587");
				props.put("mail.smtp.host", smtpHost);
				if (smtpHost.equalsIgnoreCase("smtp.gmail.com")) {
					props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
				} else {
					props.put("mail.smtp.ssl.trust", "*");
				}
				session = Session.getInstance(props, new Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});
			} catch (Exception e) {
				logger.error("Exception occurred while initializing mail", e);
			}
		}
		return session;
	}

	public static boolean sendMail(String subject, String description, String toEmailAddress) {
		if (isNullOrEmpty(toEmailAddress) || isNullOrEmpty(subject) || isNullOrEmpty(description)) {
			logger.warn("Email address not found for sending email");
			return false;
		}
		boolean bool = true;
		try {
			Session session = getMailSession();

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmailAddress.trim()));
			message.setSubject(subject);
			message.setContent(description, "text/html; charset=utf-8");
			Transport.send(message);

		} catch (Exception e) {
			logger.error("Error occurred while sending email", e);
			bool = false;
		}
		return bool;
	}

	public static boolean isNullOrEmpty(String value) {
		return value == null || value.trim().isEmpty();
	}
}
