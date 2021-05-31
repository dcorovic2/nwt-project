package com.outofoffice.notificationsservice.requestobjects;

public class AdminNotification {
	//private Long employeeId;
	private String comment;
	//private Long requestId;
	
	public AdminNotification() {}
	
	public AdminNotification(String comment) {
		super();
		//this.employeeId = employeeId;
		this.comment = comment;
		//this.requestId = requestId;
	}

//	public Long getEmployeeId() {
//		return employeeId;
//	}
//
//	public void setEmployeeId(Long employeeId) {
//		this.employeeId = employeeId;
//	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

//	public Long getRequestId() {
//		return requestId;
//	}
//
//	public void setRequestId(Long requestId) {
//		this.requestId = requestId;
//	}
	
	
	
	
}
