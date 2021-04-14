package com.outofoffice.leaverequestservice.errorhandling;

public class NotFoundException extends RuntimeException {
	
	public NotFoundException() {}
	
	public NotFoundException (String id, String type, String value, String jmbg) {
        super(String.format("%s with %s %s%s not found", type, value, id,jmbg));
    }

}
