package com.outofoffice.leaverequestservice.responseobjects;

public class LeaveRequestResponse2 {
	private Long employeeId;
	private String name;
	private String department;
	
	public LeaveRequestResponse2 () {}
	
	public LeaveRequestResponse2(Long employeeId, String name, String department) {
		super();
		this.employeeId = employeeId;
		this.name = name;
		this.department = department;
	}
	
	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	
}

