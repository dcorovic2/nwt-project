package com.outofoffice.leaverequestservice.requestobjects;

public class RequestNotification {
	private Long employeeId;
	private int notificationsId;
	private String reason;
	
	public RequestNotification() {}

	public RequestNotification(Long employeeId, int notificationsId, String reason) {
		super();
		this.employeeId = employeeId;
		this.notificationsId = notificationsId;
		this.reason = reason;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public int getNotificationsId() {
		return notificationsId;
	}

	public void setNotificationsId(int notificationsId) {
		this.notificationsId = notificationsId;
	}
}
