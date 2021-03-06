package com.outofoffice.notificationsservice.model;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "UTC")
    @Column(name = "createDate")
    private OffsetDateTime createDate;

	@Column(name="department_id")
	private Long departmentId;

	@Column(name="text")
	private String text;
	
	@Column(name="dismiss")
	private int dismiss;
	
	@Column(name="request_id")
	private Long requestId;
	
	@Column(name="employee_name")
	private String employeeName;
	
	@Column(name="request_type")
	private String requestType;
	
	public Notification() {};

	public Notification(OffsetDateTime create_date, Long departmentId, String text,NotificationsType notificationsTypeId, List<Employee> employees, int dismiss, Long requestId, String employeeName, String requestType) {
	        this.createDate = create_date;
	        this.departmentId = departmentId;
            this.notifications_type = notificationsTypeId;
	        this.text = text;
	        this.dismiss = dismiss;
	        this.employees = employees;
	        this.requestId = requestId;
	        this.employeeName = employeeName;
	        this.requestType = requestType;
	}
	public Notification(OffsetDateTime create_date, Long departmentId, String text,NotificationsType notificationsTypeId,List<Employee> employees,Long requestId,String employeeName,String requestType) {
        this.createDate = create_date;
        this.departmentId = departmentId;
        this.notifications_type = notificationsTypeId;
        this.text = text;
        this.employees = employees;
        this.requestId = requestId;
        this.employeeName = employeeName;
        this.requestType = requestType;
}

	@ManyToMany()
	@JoinTable(name = "notification_employee",
	        joinColumns = @JoinColumn(name = "notification_id"),
	        inverseJoinColumns = @JoinColumn(name = "employee_id")
	    )
	private List<Employee> employees = new ArrayList<>();
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "notifications_type_id")
	private NotificationsType notifications_type;
	
}
