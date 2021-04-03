package com.outofoffice.leaverequestservice.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import java.util.List;

import lombok.Data;

@Entity
@Table(name="notifications_type")
@Data
public class NotificationsType {
	
	@Id
    @GeneratedValue(generator = "Notifications_typeIdGenerator",strategy = GenerationType.AUTO)
    private Long id;
	
	@Column(name="code")
	private String code;

	@Column(name="display_name")
	private String displayName;

	@Column(name="name")
	private String name;
	
	public NotificationsType() {}
	
	public NotificationsType(String code, String displayName, String name) {
		super();
		//this.id = id;
		this.code = code;
		this.displayName = displayName;
		this.name = name;
	}
	
	//@OneToMany(fetch = FetchType.EAGER, mappedBy = "notifications_type")
	//private List<LeaveRequest> requests;

}
