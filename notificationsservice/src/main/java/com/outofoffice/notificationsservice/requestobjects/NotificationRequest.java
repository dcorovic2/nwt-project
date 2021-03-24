package com.outofoffice.notificationsservice.requestobjects;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

public class NotificationRequest {
//	private Long id;
	
	@NotNull
	@Range(min=1, max=20)
	private int departmentId;
	
	@NotNull(message = "Text can not be empty")
	private String text;
	
	public  NotificationRequest() {}

	public NotificationRequest(int departmentId,String text) {
		super();
		this.departmentId = departmentId;
		this.text = text;
	};

		public int getDepartmentId() {
			return departmentId;
		}

		public void setDepartmentId(int departmentId) {
			this.departmentId = departmentId;
		}


		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}


}
