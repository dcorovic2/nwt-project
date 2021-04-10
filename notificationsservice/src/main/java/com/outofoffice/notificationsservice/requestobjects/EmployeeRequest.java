package com.outofoffice.notificationsservice.requestobjects;


public class EmployeeRequest {
	
	private Long allowance;
	private Long departmentId;
	private String email;
	private String firstnameLastName;
	
	public  EmployeeRequest() {}
	
	public EmployeeRequest(Long allowance, Long departmentId, String email, String firstnameLastName) {
		super();
		this.allowance = allowance;
		this.departmentId = departmentId;
		this.email = email;
		this.firstnameLastName = firstnameLastName;
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
	public String getFirstnameLastName() {
		return firstnameLastName;
	}
	public void setFirstnameLastName(String firstnameLastName) {
		this.firstnameLastName = firstnameLastName;
	}
	
}
