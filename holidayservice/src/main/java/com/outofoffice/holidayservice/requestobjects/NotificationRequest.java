package com.outofoffice.holidayservice.requestobjects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class NotificationRequest {	
	@NotNull(message = "Text cannot be empty")
	@Size(min = 2, max = 50)
	private String text;
	
	public NotificationRequest(String createDate, String text) {
		super();
		this.text = text;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
}
