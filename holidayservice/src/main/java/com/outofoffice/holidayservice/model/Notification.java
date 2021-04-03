package com.outofoffice.holidayservice.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Column;

import lombok.Data;


@Entity
@Table(name = "notification")
@Data
public class Notification {
	
	@Id
    @GeneratedValue(generator = "NotificationIdGenerator", strategy = GenerationType.AUTO)
    private long id;
	
	@ManyToMany()
	@JoinTable(name = "notification_employee",
	        joinColumns = @JoinColumn(name = "notification_id"),
	        inverseJoinColumns = @JoinColumn(name = "employee_id")
	    )
	private List<Employee> employees = new ArrayList<>();

	@Column(name = "text")
	private String text;
	
	public Notification() {}
	
	public Notification(String text, List<Employee> employees) {
		super();
		this.text = text;
		this.employees = employees;
	}
}
