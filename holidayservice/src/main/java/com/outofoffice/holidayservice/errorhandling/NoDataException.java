package com.outofoffice.holidayservice.errorhandling;

public class NoDataException extends RuntimeException{
	public NoDataException() {
   	 super("No Data Found");
    }
}
