package com.outofoffice.leaverequestservice.requestobjects;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class LeaveStatusRequest {
	
	private int statusId;
	private int notificationsId;
	private String reason;
	
	public LeaveStatusRequest() {}

	public LeaveStatusRequest(int statusId, int notificationsId, String reason) {
		super();
		this.statusId = statusId;
		this.notificationsId = notificationsId;
		this.reason = reason;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public int getNotificationsId() {
		return notificationsId;
	}

	public void setNotificationsId(int notificationsId) {
		this.notificationsId = notificationsId;
	}
	
	
	
	
	

}
