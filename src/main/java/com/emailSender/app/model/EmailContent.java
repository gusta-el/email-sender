package com.emailSender.app.model;

import java.util.List;

public class EmailContent {

	private List<String> toAddressesTo;
	private List<String> toAddressesCc;
	private List<String> toAddressesCo;
	private String subject;
	private String message;
	
	public EmailContent(List<String> toAddressesTo, List<String> toAddressesCc, List<String> toAddressesCo,
			String subject, String message) {
		super();
		this.toAddressesTo = toAddressesTo;
		this.toAddressesCc = toAddressesCc;
		this.toAddressesCo = toAddressesCo;
		this.subject = subject;
		this.message = message;
	}

	public List<String> getToAddressesTo() {
		return toAddressesTo;
	}

	public void setToAddressesTo(List<String> toAddressesTo) {
		this.toAddressesTo = toAddressesTo;
	}

	public List<String> getToAddressesCc() {
		return toAddressesCc;
	}

	public void setToAddressesCc(List<String> toAddressesCc) {
		this.toAddressesCc = toAddressesCc;
	}

	public List<String> getToAddressesCo() {
		return toAddressesCo;
	}

	public void setToAddressesCo(List<String> toAddressesCo) {
		this.toAddressesCo = toAddressesCo;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
