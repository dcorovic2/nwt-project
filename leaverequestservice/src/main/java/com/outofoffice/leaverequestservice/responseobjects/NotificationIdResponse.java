package com.outofoffice.leaverequestservice.responseobjects;

public class NotificationIdResponse {
	private Long notificationId;
	private Long requestId;
	
	public NotificationIdResponse() {}
	
	public NotificationIdResponse(Long notificationId, Long requestId) {
		super();
		this.notificationId = notificationId;
		this.requestId = requestId;
	}

	public Long getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(Long notificationId) {
		this.notificationId = notificationId;
	}

	public Long getRequestId() {
		return requestId;
	}

	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}
	
	
	
}
