package com.outofoffice.holidayservice.requestobjects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class HolidayTypeRequest {
	@NotNull(message = "Code cannot be empty")
	@Size(min = 5, max = 25)
	private String code;
	
	@NotNull(message = "Display name cannot be empty")
	@Size(min = 5, max = 25)
	private String displayName;
	
	@NotNull(message = "Name cannot be empty")
	@Size(min = 5, max = 25)
	private String name;
	
	@NotNull(message = "Type cannot be empty")
	@Size(min = 5, max = 25)
	private String type;
	
	public HolidayTypeRequest() {
		super();
	}

	public HolidayTypeRequest(String code, String displayName, String name, String type) {
		super();
		this.code = code;
		this.displayName = displayName;
		this.name = name;
		this.type = type;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
