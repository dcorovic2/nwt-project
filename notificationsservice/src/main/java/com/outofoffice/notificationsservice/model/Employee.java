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
@Data
public class Employee {

	@Id
    @GeneratedValue(generator = "EmployeeIdGenerator", strategy = GenerationType.AUTO)
	private long interni_id;
	
	@Column(name = "employee_id")
    private Long id;

	@Column(name="allowance")
	private Long allowance;

	@Column(name="department_id")
	private Long department_id;


    @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
            +"[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
            +"(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
                 message="{invalid.email}")
	@Column(name="email")
	private String email;


	@Column(name="firstNameLastName")
	private String firstNameLastName;
	
	
	public Employee() {};
	
	public Employee(long id,Long allowance,Long departmentId,String email,String firstNameLastName) {
        this.allowance =allowance;
        this.department_id = departmentId;
        this.email = email;
        this.id = id;
        this.firstNameLastName = firstNameLastName;
}
	
//	@ManyToMany(mappedBy = "employees")
//    private List<Holiday> holidays = new ArrayList<>();
	
//	@ManyToMany(mappedBy = "employees")
//	private List<Notification> notifications;
	
}
