package com.outofoffice.notificationsservice.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Entity
@Table(name= "employee")
@Data
public class Employee {
    
	@Id
    @GeneratedValue(generator = "EmployeeIdGenerator",strategy = GenerationType.AUTO)
    private Long id;
	
	@Column(name="name")
	private String name;

	@Column(name="allowance")
	private String allowance;

	@Column(name="company_id")
	private Integer companyId;

	@Column(name="department_id")
	private Integer departmentId;

	@Column(name="email")
	private String email;

	@Column(name="firstname_last_name")
	private String firstnameLastName;

	@Column(name="hire_date")
	private Date hireDate;
	
	@Column(name="jmbg")
	private String jmbg;

	@Column(name="job_role")
	private String jobRole;

	@Column(name="phone_number")
	private String phoneNumber;

	@Column(name="remaining_days")
	private Integer remainingDays;
	
	@Column(name="role_id")
	private Integer roleId;
	
	public Employee() {};
	
	@ManyToMany(mappedBy = "employees")
    private List<Holiday> holidays;
	
	@ManyToMany
	@JoinTable(name = "notification_employee",
	        joinColumns = @JoinColumn(name = "employee_id"),
	        inverseJoinColumns = @JoinColumn(name = "notification_id"))
    private List<Notification> notifications;
}
