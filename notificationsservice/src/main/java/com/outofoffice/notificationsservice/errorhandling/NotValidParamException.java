package com.outofoffice.notificationsservice.errorhandling;

public class NotValidParamException extends RuntimeException{
	public NotValidParamException (String msg) {
        super(String.format(msg));
    }
}
