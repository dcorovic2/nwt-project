package com.outofoffice.notificationsservice.responseobjects;

public class RequestNotification {
	private Long employeeId;
	private Long notificationsId;
	private String reason;
	private Long requestId;
	
	public RequestNotification() {}

	public RequestNotification(Long employeeId, Long notificationsId, String reason, Long requestId) {
		super();
		this.employeeId = employeeId;
		this.notificationsId = notificationsId;
		this.reason = reason;
		this.requestId = requestId;
	}

	public Long getRequestId() {
		return requestId;
	}

	public void setRequestId(Long requestId) {
		this.requestId = requestId;
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

	public Long getNotificationsId() {
		return notificationsId;
	}

	public void setNotificationsId(Long notificationsId) {
		this.notificationsId = notificationsId;
	}
}
