package com.outofoffice.outofoffice.model;
import java.time.OffsetDateTime;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name= "employee")
@Data
public class Employee {
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="allowance")
	private Long allowance;
	
	@Email(message="Email not valid")
	@Column(name="email")
	private String email;

	@NotNull(message="Name can't be empty")
	@Column(name="firstname_last_name")
	private String firstnameLastName;

	@Column(name="hire_date")
	private OffsetDateTime hireDate;
	
	@Length(min = 13, max = 13, message="Jmbg must contain 13 numbers.")
	@Pattern(regexp="[0-9]+", message="Only numbers are allowed for jmbg")
	@Column(name="jmbg",unique = true)
	private String jmbg;

	@Column(name="job_role")
	private String jobRole;

	@Column(name="phone_number")
	private String phoneNumber;

	@Column(name="remaining_days")
	private Integer remainingDays;
	
	@Column(name="username")
	private String username;
	
	public Employee() {};
	
	@ManyToOne
	@JoinColumn(name = "department_id", referencedColumnName = "id")
	private Department department;
	
	@ManyToOne
	@JoinColumn(name = "role_id", referencedColumnName = "id")
	private Role role;

}
