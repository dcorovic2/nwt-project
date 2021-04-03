package com.outofoffice.notificationsservice.requestobjects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class NotificationTypeRequest {

	@Size(min=1, max=1, message = "Invalid code input")
	private String code;
	
	@NotNull(message = "Display name can not be empty")
	private String displayName;
	
	@NotNull(message = "Name can not be empty")
	private String name;
	
	
	public  NotificationTypeRequest() {}
	
	public NotificationTypeRequest(String code, String displayName, String name) {
		super();
		this.code = code;
		this.displayName = displayName;
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
