package com.outofoffice.leaverequestservice.responseobjects;

public class TypeResponse {
	private int vacation;
	private int sick;
	
	public TypeResponse() {}
	
	public TypeResponse(int vacation, int sick) {
		super();
		this.vacation = vacation;
		this.sick = sick;
	}

	public int getVacation() {
		return vacation;
	}

	public void setVacation(int vacation) {
		this.vacation = vacation;
	}

	public int getSick() {
		return sick;
	}

	public void setSick(int sick) {
		this.sick = sick;
	}
	
	
	
	

}
