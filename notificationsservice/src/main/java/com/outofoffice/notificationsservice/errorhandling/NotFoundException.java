package com.outofoffice.notificationsservice.errorhandling;

public class NotFoundException extends RuntimeException{
	public NotFoundException() {
		super(String.format("CLIENT not found"));
	}
	public NotFoundException (String id, String type, String value, String notificationType) {
        super(String.format("%s with %s %s%s not found", type, value, id,notificationType));
    }
}
