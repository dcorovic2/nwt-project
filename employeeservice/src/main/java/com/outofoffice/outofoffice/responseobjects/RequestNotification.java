package com.outofoffice.outofoffice.responseobjects;



public class RequestNotification {
	private Long employeeId;
	private Long notificationsId;
	private Long statusId;
	private String reason;
	private Long requestId;
	private long restDays;
	private boolean backup;
	
	public RequestNotification() {}

	public RequestNotification(Long employeeId, Long notificationsId, Long statusId, String reason, Long requestId, long restDays) {
		super();
		this.employeeId = employeeId;
		this.notificationsId = notificationsId;
		this.reason = reason;
		this.requestId = requestId;
		this.restDays = restDays;
		this.statusId = statusId;
	}

	public boolean getBackup() {
		return backup;
	}

	public void setBackup(boolean backup) {
		this.backup = backup;
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

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
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
	public long getRestDays() {
		return restDays;
	}
	public void setRestDays(long restDays) {
		this.restDays = restDays;
	}
}
