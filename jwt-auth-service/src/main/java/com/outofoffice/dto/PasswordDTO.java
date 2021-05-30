package com.outofoffice.dto;

import javax.validation.constraints.Size;

public class PasswordDTO {
	 @Size(min = 8, message = "Minimum password length: 8 characters")
	  private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public PasswordDTO () {}
	public PasswordDTO(@Size(min = 8, message = "Minimum password length: 8 characters") String password) {
		super();
		this.password = password;
	}
	 
}
