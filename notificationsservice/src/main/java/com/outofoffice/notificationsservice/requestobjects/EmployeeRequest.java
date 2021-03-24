package com.outofoffice.notificationsservice.requestobjects;


public class EmployeeRequest {
	
	private int allowance;
	private int departmentId;
	private String email;
	private String firstnameLastName;
	
	public  EmployeeRequest() {}
	
	public EmployeeRequest(int allowance, int departmentId, String email, String firstnameLastName) {
		super();
		this.allowance = allowance;
		this.departmentId = departmentId;
		this.email = email;
		this.firstnameLastName = firstnameLastName;
	}
	public int getAllowance() {
		return allowance;
	}
	public void setAllowance(int allowance) {
		this.allowance = allowance;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
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
