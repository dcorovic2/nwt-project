package com.outofoffice.notificationsservice.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Table(name= "employee")
@Data
public class Employee {
    
	@Id
    @GeneratedValue(generator = "EmployeeIdGenerator",strategy = GenerationType.AUTO)
    private Long id;

	@Column(name="allowance")
	private int allowance;

	@Column(name="department_id")
	private int departmentId;


    @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
            +"[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
            +"(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
                 message="{invalid.email}")
	@Column(name="email")
	private String email;


	@Column(name="firstname_last_name")
	private String firstnameLastName;
	
	
	public Employee() {};
	
	public Employee(int allowance,int departmentId,String email,String firstnameLastName) {
        this.allowance =allowance;
        this.departmentId = departmentId;
        this.email = email;
        this.firstnameLastName = firstnameLastName;
}
	
//	@ManyToMany(mappedBy = "employees")
//    private List<Holiday> holidays = new ArrayList<>();
	
//	@ManyToMany(mappedBy = "employees")
//	private List<Notification> notifications;
}
