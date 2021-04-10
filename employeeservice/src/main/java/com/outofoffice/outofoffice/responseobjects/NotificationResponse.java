package com.outofoffice.outofoffice.responseobjects;

import lombok.Data;

@Data
public class NotificationResponse {
	private Long id;
	private Long allowance;
	private Long department_id;
	private String email;
	private String firstNameLastName;
	
	public NotificationResponse() {}

	public NotificationResponse(Long id, Long allowance, Long department_id, String email, String firstNameLastName) {
		super();
		this.id = id;
		this.allowance = allowance;
		this.department_id = department_id;
		this.email = email;
		this.firstNameLastName = firstNameLastName;
	};
	

}
