package com.outofoffice.notificationsservice.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;

import lombok.Data;


@Entity
@Table(name="notification")
@Data
public class Notification {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="create_date")
	private Date createDate;

	@Column(name="department_id")
	private Integer departmentId;

	@Column(name="employee_id")
	private Integer employeeId;

	@Column(name="role_id")
	private Integer roleId;

	@Column(name="text")
	private String text;
	
	public Notification() {};
}
