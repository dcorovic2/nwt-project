package com.outofoffice.outofoffice.requestobjects;
import java.time.OffsetDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class EmployeeRequest {
	
	public Long allowance;
	@Email
	public String email;
	@NotNull
	public String firstnameLastName;
	public OffsetDateTime hireDate;
	public String jmbg;
	public String jobRole;
	public String phoneNumber;
	public Integer remainingDays;
	public Long departmentId; 
	public Long roleId;
	public String username;
	public String password;
	
	public EmployeeRequest(Long allowance, String email, String firstnameLastName, OffsetDateTime hireDate, String jmbg,
			String jobRole, String phoneNumber, Integer remainingDays, String username, String password, Long departmentId, Long roleId) {
		super();
		this.allowance = allowance;
		this.email = email;
		this.firstnameLastName = firstnameLastName;
		this.hireDate = hireDate;
		this.jmbg = jmbg;
		this.jobRole = jobRole;
		this.phoneNumber = phoneNumber;
		this.remainingDays = remainingDays;
		this.departmentId = departmentId; 
		this.username = username;
		this.password = password;
		this.roleId = roleId; 
	}

		
}
