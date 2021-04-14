package com.outofoffice.holidayservice.errorhandling;

public class NotFoundException extends RuntimeException{
	public NotFoundException (String id, String type, String value, String notificationType) {
        super(String.format("%s with %s %s%s not found", type, value, id,notificationType));
    }
	
	public NotFoundException () {
        super(String.format("Client not found"));
    }
}
