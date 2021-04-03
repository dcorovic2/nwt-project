package com.outofoffice.holidayservice.requestobjects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class EmployeeRequest {
	@NotNull(message = "Allowance cannot be empty")
	private int allowance;
	
	@NotNull(message = "Name cannot be empty")
	@Size(min = 5, max = 30)
	private String firstnameLastName;
	
	@NotNull(message = "Remaining days cannot be empty")
	private int remainingDays;
	
	public int getAllowance() {
		return allowance;
	}
	public void setAllowance(int allowance) {
		this.allowance = allowance;
	}
	public String getFirstnameLastName() {
		return firstnameLastName;
	}
	public void setFirstnameLastName(String firstnameLastName) {
		this.firstnameLastName = firstnameLastName;
	}
	public int getRemainingDays() {
		return remainingDays;
	}
	public void setRemainingDays(int remainingDays) {
		this.remainingDays = remainingDays;
	}
	
	public EmployeeRequest() {	}
	
	public EmployeeRequest(int allowance, String firstnameLastName, int remainingDays) {
		super();
		this.allowance = allowance;
		this.firstnameLastName = firstnameLastName;
		this.remainingDays = remainingDays;
	}

}
