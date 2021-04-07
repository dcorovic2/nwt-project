package com.outofoffice.holidayservice.responseobjects;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class NotificationResponse {
	List<Long> id = new ArrayList();
	String text;
	
	public NotificationResponse(List<Long> id, String text) {
		super();
		this.id = id;
		this.text = text;
	}

	public NotificationResponse() {
		super();
	}

}
