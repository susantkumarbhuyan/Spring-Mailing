package com.kumarsusantcontroller;

public class MailModel {
	private String mailTo;
	private String message;

	public String mailTo() {
		return mailTo;
	}

	public void mailTo(String mailTo) {
		this.mailTo = mailTo;
	}

	public String message() {
		return message;
	}

	public void message(String message) {
		this.message = message;
	}

}
