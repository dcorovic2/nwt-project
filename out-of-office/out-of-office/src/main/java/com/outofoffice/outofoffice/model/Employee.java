package com.outofoffice.outofoffice.model;

import java.sql.Date;
import java.time.OffsetDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
	
	@Column(name="allowance")
	private String allowance;

	@Column(name="email")
	private String email;

	@NotEmpty
	@Column(name="firstname_last_name")
	private String firstnameLastName;

	@Column(name="hire_date")
	private OffsetDateTime hireDate;
	
	@Column(name="jmbg",unique = true)
	private String jmbg;

	@Column(name="job_role")
	private String jobRole;

	@Column(name="phone_number")
	private String phoneNumber;

	@Column(name="remaining_days")
	private Integer remainingDays;
	
	public Employee() {};
	
	@ManyToOne//(fetch = FetchType.LAZY)
	@JoinColumn(name = "department_id", referencedColumnName = "id")
	private Department department;
	
	@ManyToOne//(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id", referencedColumnName = "id")
	private Role role;
	
//	@OneToOne(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	private Auth auth;
}
