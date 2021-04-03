package com.outofoffice.holidayservice.errorhandling;

public class NotValidParamException extends RuntimeException{
	public NotValidParamException (String msg) {
        super(String.format(msg));
    }
}
