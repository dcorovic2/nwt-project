package com.outofoffice.leaverequestservice.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import java.util.List;

import javax.persistence.Column;

import lombok.Data;

@Entity
@Table(name="leave_status")
@Data
public class LeaveStatus {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="code")
	private String code;

	@Column(name="display_name")
	private String displayName;
	
	@Column(name="name")
	private String name;
	
	@Column(name="reason")
	private String reason;
	
	public LeaveStatus() {};
	
	public LeaveStatus(String code, String displayName, String name, String reason) {
		super();
		//this.id = id;
		this.code = code;
		this.displayName = displayName;
		this.name = name;
		this.reason = reason;
		//this.requests = requests;
	}



	//@OneToMany(fetch = FetchType.EAGER, mappedBy = "leave_status")
	//private List<LeaveRequest> requests;
}
