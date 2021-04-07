package com.outofoffice.outofoffice.requestobjects;

import lombok.Data;

@Data
public class LoginRequest {

	public String username;
	public String password;
	public Long employee_id;
	
	public LoginRequest(String username, String password, Long employee_id) {
		super();
		this.username = username;
		this.password = password;
		this.employee_id = employee_id;
	}
	
	
}
