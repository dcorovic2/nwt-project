package com.outofoffice.leaverequestservice.errorhandling;

public class NotSucesfullException extends RuntimeException {
	
	public NotSucesfullException() {
		super(String.format("Status and notification do not match"));
	}


}
