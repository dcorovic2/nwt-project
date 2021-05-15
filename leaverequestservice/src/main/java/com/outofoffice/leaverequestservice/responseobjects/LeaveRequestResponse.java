package com.outofoffice.leaverequestservice.responseobjects;

import java.util.List;

import lombok.Data;

@Data
public class LeaveRequestResponse {
	public List<Long>employee_ids;
	public boolean allowance;
	public Long days;
	public Integer departmentallowance;
	
	public LeaveRequestResponse() {};
	public LeaveRequestResponse(List<Long> employee_ids, boolean allowance, Long days, Integer departmentallowance) {
		super();
		this.employee_ids = employee_ids;
		this.allowance = allowance;
		this.departmentallowance = departmentallowance;
		this.days = days;
	}
	
}