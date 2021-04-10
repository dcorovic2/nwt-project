package com.outofoffice.holidayservice.requestobjects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class HolidayTypeRequest {
	@NotNull(message = "Code cannot be empty")
	@Size(min = 5, max = 25)
	private String code;
	
	@NotNull(message = "Display name cannot be empty")
	@Size(min = 5, max = 25)
	private String displayName;
	
	@NotNull(message = "Text cannot be empty")
	@Size(min = 5, max = 100)
	private String text;
	
	@NotNull(message = "Type cannot be empty")
	@Size(min = 5, max = 25)
	private String type;
	
	public HolidayTypeRequest() {
		super();
	}

	public HolidayTypeRequest(String code, String displayName, String text, String type) {
		super();
		this.code = code;
		this.displayName = displayName;
		this.text = text;
		this.type = type;
	}

}
