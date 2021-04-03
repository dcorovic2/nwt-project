package com.outofoffice.leaverequestservice.requestobjects;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class LeaveStatusRequest {
	
	private int statusId;
	private int notificationsId;
	
	public LeaveStatusRequest() {}

	public LeaveStatusRequest(int statusId, int notificationsId) {
		super();
		this.statusId = statusId;
		this.notificationsId = notificationsId;
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
