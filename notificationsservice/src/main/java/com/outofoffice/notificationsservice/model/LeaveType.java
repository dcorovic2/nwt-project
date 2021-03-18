package com.outofoffice.notificationsservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;

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

}
