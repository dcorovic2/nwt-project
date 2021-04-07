package com.outofoffice.outofoffice.errorhandling;

public class NotValidParamException extends RuntimeException{
	public NotValidParamException (String Param, String additional_description) {
        super(String.format("%s param is not valid. %s", Param, additional_description));
    }
}
