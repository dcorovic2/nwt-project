package com.outofoffice.notificationsservice.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn; 

import lombok.Data;


@Entity
@Table(name="notification")
@Data
public class Notification {
	
	@Id
    @GeneratedValue(generator = "NotificationIdGenerator",strategy = GenerationType.AUTO)
    private Long id;

	@Column(name="create_date")
	private Date createDate;

	@Column(name="department_id")
	private Integer departmentId;

	@Column(name="employee_id")
	private Integer employeeId;

	@Column(name="text")
	private String text;
	
	public Notification() {};
	
	@ManyToMany(mappedBy = "notifications")
	private List<Employee> employees;
	
	@ManyToOne//(fetch = FetchType.LAZY)
	@JoinColumn(name = "notifications_type_id", referencedColumnName = "id")
	private NotificationsType notifications_type;
}
