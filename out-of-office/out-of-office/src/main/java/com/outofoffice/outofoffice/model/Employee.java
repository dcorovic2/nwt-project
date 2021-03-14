package com.outofoffice.outofoffice.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name= "employee")
@Data
public class Employee {
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//  @SequenceGenerator(name = "employee_seq_generator", sequenceName = "seq_employee", allocationSize = 1)
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
	
}
