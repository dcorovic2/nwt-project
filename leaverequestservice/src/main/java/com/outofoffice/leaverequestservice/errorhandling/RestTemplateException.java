package com.outofoffice.leaverequestservice.errorhandling;

public class RestTemplateException extends RuntimeException {
	
	public RestTemplateException() {}
	
	public RestTemplateException (String uri) {
        super("No instances available. Could not connect to: " + uri);
    }

}
