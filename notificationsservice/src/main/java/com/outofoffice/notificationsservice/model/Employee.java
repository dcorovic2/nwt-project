package com.outofoffice.notificationsservice.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;


import lombok.Data;

@Entity
@Table(name= "employee")

public class Employee {

	@Id
    @GeneratedValue(generator = "EmployeeIdGenerator", strategy = GenerationType.AUTO)
	private long interni_id;
	
	@Column(name = "employee_id")
    private Long id;

	@Column(name="allowance")
	private Long allowance;

	@Column(name="department_id")
	private Long departmentId;


    @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
            +"[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
            +"(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
                 message="{invalid.email}")
	@Column(name="email")
	private String email;


	@Column(name="firstname_last_name")
	private String firstnameLastName;
	
	
	public Employee() {};
	
	public Employee(Long allowance,Long departmentId,String email,String firstnameLastName, long id) {
        this.allowance =allowance;
        this.departmentId = departmentId;
        this.email = email;
        this.id = id;
        this.firstnameLastName = firstnameLastName;
}
	
//	@ManyToMany(mappedBy = "employees")
//    private List<Holiday> holidays = new ArrayList<>();
	
//	@ManyToMany(mappedBy = "employees")
//	private List<Notification> notifications;
	

	public Long getAllowance() {
		return allowance;
	}

	public void setAllowance(Long allowance) {
		this.allowance = allowance;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstnameLastName() {
		return firstnameLastName;
	}

	public void setFirstnameLastName(String firstnameLastName) {
		this.firstnameLastName = firstnameLastName;
	}

}
