package com.outofoffice.holidayservice.errorhandling;

public class NotSucesfullException extends RuntimeException {
	public NotSucesfullException() {
		super("Request was not successfull");
	}

}
