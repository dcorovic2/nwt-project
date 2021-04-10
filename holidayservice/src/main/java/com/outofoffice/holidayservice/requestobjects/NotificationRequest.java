package com.outofoffice.holidayservice.requestobjects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class NotificationRequest {	
	@NotNull(message = "Text cannot be empty")
	@Size(min = 2, max = 50)
	private String text;
	
	public NotificationRequest(String createDate, String text) {
		super();
		this.text = text;
	}

	public NotificationRequest() {
		super();
	}
	
}
