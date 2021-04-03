package com.outofoffice.notificationsservice.requestobjects;


public class HolidayRequest {

	private String displayName;
	private String name;
	private String type;
	
	public HolidayRequest() {}
	
	public HolidayRequest(String displayName, String name, String type) {
		super();
		this.displayName = displayName;
		this.name = name;
		this.type = type;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
