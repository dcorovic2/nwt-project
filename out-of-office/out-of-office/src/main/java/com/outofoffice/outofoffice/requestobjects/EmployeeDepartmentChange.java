package com.outofoffice.outofoffice.requestobjects;

import lombok.Data;

@Data
public class EmployeeDepartmentChange {
	public String department_code;
	public String employee_jmbg;
	public EmployeeDepartmentChange(String department_code, String employee_jmbg) {
		super();
		this.department_code = department_code;
		this.employee_jmbg = employee_jmbg;
	}
	
}
