package com.outofoffice.leaverequestservice.errorhandling;

public class NoDataException extends RuntimeException {
	public NoDataException() {
		super(String.format("No data in DB"));
	}

}
