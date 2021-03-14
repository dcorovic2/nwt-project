package com.outofoffice.outofoffice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
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
}
