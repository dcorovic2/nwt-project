package com.outofoffice.leaverequestservice.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Table(name="leave_type")
@Data
public class LeaveType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="code")
	private String code;

	@Column(name="display_name")
	private String displayName;

	@Column(name="name")
	private String name;
	
	public LeaveType() {};
	
	
	
	public LeaveType(String code, String displayName, String name) {
		super();
		//this.id = id;
		this.code = code;
		this.displayName = displayName;
		this.name = name;
		//this.requests = requests;
	}

	//@OneToMany(fetch = FetchType.EAGER, mappedBy = "leave_type")
	//private List<LeaveRequest> requests;

}
