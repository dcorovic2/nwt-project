package com.outofoffice.outofoffice.responseobjects;

import java.util.List;

import lombok.Data;

@Data
public class LeaveRequestResponse {
	private List<Long> employee_ids;
	private boolean allowance;
	private Long days;
	private Integer departmentallowance;

	public LeaveRequestResponse() {
	};

	public LeaveRequestResponse(List<Long> employee_ids, boolean allowance, Long days, Integer departmentallowance) {
		super();
		this.employee_ids = employee_ids;
		this.allowance = allowance;
		this.departmentallowance = departmentallowance;
		this.days = days;
	}
}
