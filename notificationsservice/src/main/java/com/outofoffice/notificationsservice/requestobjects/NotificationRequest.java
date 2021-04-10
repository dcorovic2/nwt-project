package com.outofoffice.notificationsservice.requestobjects;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


public class NotificationRequest {
	
	@Min(value = 1,
            message = "Department ID must be between 1 to 20")
	@Max(value = 20,
            message = "Department ID must be between 1 to 20")
	private Long departmentId;
	
	@NotNull(message = "Text can not be empty")
	private String text;
	
	@Min(value = 0,
            message = "Dismiss must be between 0 or 1")
	@Max(value = 1,
            message = "Dismiss must be between 0 or 1")
	private int dismiss;
	
	public  NotificationRequest() {}

	public NotificationRequest(Long departmentId,String text, int dismiss) {
		super();
		this.departmentId = departmentId;
		this.dismiss = dismiss;
		this.text = text;
	};

		public Long getDepartmentId() {
			return departmentId;
		}

		public void setDepartmentId(Long departmentId) {
			this.departmentId = departmentId;
		}
		
		public int getDismiss() {
			return dismiss;
		}

		public void setDismiss(int dismiss) {
			this.dismiss = dismiss;
		}


		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}


}
