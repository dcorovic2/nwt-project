package com.outofoffice.leaverequestservice.requestobjects;

public class RequestEmployee {
	private Long employeeId;
	private long restDays;
	
	public RequestEmployee() {}
	
	public RequestEmployee(Long employeeId, long restDays) {
		super();
		this.employeeId = employeeId;
		this.restDays = restDays;
	}
	
	public Long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
	public long getRestDays() {
		return restDays;
	}
	public void setRestDays(long restDays) {
		this.restDays = restDays;
	}
	
	

}
