package com.outofoffice.notificationsservice.errorhandling;

public class NoDataException extends RuntimeException{
	public NoDataException() {
   	 super("No Data Found");
    }
}
