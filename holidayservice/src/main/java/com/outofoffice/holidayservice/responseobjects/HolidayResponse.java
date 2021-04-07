package com.outofoffice.holidayservice.responseobjects;

import lombok.Data;

@Data
public class HolidayResponse {
	String name;
    Long id;
 
    public HolidayResponse() {}

	public HolidayResponse(String name, Long id) {
		super();
		this.name = name;
		this.id = id;
	};

}
