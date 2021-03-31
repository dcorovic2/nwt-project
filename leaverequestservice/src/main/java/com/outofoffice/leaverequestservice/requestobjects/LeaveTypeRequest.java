package com.outofoffice.leaverequestservice.requestobjects;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class LeaveTypeRequest {
	
	private Long id;
	private String code;
	private String displayName;
	private String name;
	public LeaveTypeRequest() {}
	
	public LeaveTypeRequest(Long id, String code, String displayName, String name) {
		super();
		this.id = id;
		this.code = code;
		this.displayName = displayName;
		this.name = name;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	};
	
	
}
