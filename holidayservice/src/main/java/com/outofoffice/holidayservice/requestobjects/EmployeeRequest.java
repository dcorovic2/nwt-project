package com.outofoffice.holidayservice.requestobjects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class EmployeeRequest {	
	private long id;
	
	@NotNull(message = "Name cannot be empty")
	@Size(min = 5, max = 30)
	private String firstnameLastName;
	
	public String getFirstnameLastName() {
		return firstnameLastName;
	}
	public void setFirstnameLastName(String firstnameLastName) {
		this.firstnameLastName = firstnameLastName;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public EmployeeRequest() {	}
	
	public EmployeeRequest(long id, String firstnameLastName) {
		super();
		this.firstnameLastName = firstnameLastName;
		this.id = id;
	}

}
