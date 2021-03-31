package com.outofoffice.leaverequestservice.requestobjects;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class NotificationsTypeRequest {
	
	private String code;
	private String displayName;
	private String name;
	
	public NotificationsTypeRequest() {}

	public NotificationsTypeRequest(String code, String displayName, String name) {
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
	};	
	
}
