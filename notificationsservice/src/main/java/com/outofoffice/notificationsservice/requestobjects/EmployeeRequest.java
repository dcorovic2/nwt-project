package com.outofoffice.notificationsservice.requestobjects;

import lombok.Data;

@Data
public class EmployeeRequest {
	
	private Long allowance;
	private Long departmentId;
	private String email;
	private String firstNameLastName;
	
	public  EmployeeRequest() {}
	
	public EmployeeRequest(Long allowance, Long departmentId, String email, String firstnameLastName) {
		super();
		this.allowance = allowance;
		this.departmentId = departmentId;
		this.email = email;
		this.firstNameLastName = firstnameLastName;
	}
	
}
