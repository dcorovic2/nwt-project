package com.outofoffice.outofoffice.errorhandling;

public class NoDataException extends RuntimeException{
             public NoDataException() {
            	 super("No Data Found");
             }
}
