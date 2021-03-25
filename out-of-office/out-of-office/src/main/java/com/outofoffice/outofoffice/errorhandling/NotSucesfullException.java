package com.outofoffice.outofoffice.errorhandling;

public class NotSucesfullException extends RuntimeException{
	public NotSucesfullException() {
		super("Request was not successfull");
	}
}
