package com.outofoffice.leaverequestservice.requestobjects;

public class AdminNotification {
	private Long employeeId;
	private String comment;
	private Long requestId;
	private String displayName;
	
	
	
	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public AdminNotification() {}
	
	public AdminNotification(Long employeeId, String comment, Long requestId, String displayName) {
		super();
		this.employeeId = employeeId;
		this.comment = comment;
		this.requestId = requestId;
		this.displayName = displayName;
		
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Long getRequestId() {
		return requestId;
	}

	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}
	
	
	
	
}
