package com.outofoffice.notificationsservice.requestobjects;

import java.time.OffsetDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class AddedEmployee {

	private Long id;
	private Long allowance;
	private Long departmentId;
	private String email;
	private String firstNameLastName;

	public AddedEmployee() {
	}

	public AddedEmployee(Long id, Long allowance, Long departmentId, String email, String firstnameLastName) {
		super();
		this.id = id;
		this.allowance = allowance;
		this.departmentId = departmentId;
		this.email = email;
		this.firstNameLastName = firstnameLastName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAllowance() {
		return allowance;
	}

	public void setAllowance(Long allowance) {
		this.allowance = allowance;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstNameLastName() {
		return firstNameLastName;
	}

	public void setFirstNameLastName(String firstNameLastName) {
		this.firstNameLastName = firstNameLastName;
	}
	
	

}
