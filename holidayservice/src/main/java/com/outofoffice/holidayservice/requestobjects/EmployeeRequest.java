package com.outofoffice.holidayservice.requestobjects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class EmployeeRequest {	
	@NotNull(message = "Id cannot be empty")
	private long id;
	
	@NotNull(message = "Name cannot be empty")
	@Size(min = 5, max = 30)
	private String firstnameLastName;
	
	public EmployeeRequest() {	}
	
	public EmployeeRequest(long id, String firstnameLastName) {
		super();
		this.firstnameLastName = firstnameLastName;
		this.id = id;
	}

}
