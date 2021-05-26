package com.outofoffice.leaverequestservice.responseobjects;

import java.time.LocalDate;

public class EmployeesOnLeaveResponse {
	private String employeeName;
	private String departmentName;
	private String LeaveType;
	private LocalDate startDate;
	private LocalDate endDate;
	
	public EmployeesOnLeaveResponse() {}

	public EmployeesOnLeaveResponse(String employeeName, String departmentName, String leaveType, LocalDate startDate,
			LocalDate endDate) {
		super();
		this.employeeName = employeeName;
		this.departmentName = departmentName;
		LeaveType = leaveType;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getLeaveType() {
		return LeaveType;
	}

	public void setLeaveType(String leaveType) {
		LeaveType = leaveType;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	

}
