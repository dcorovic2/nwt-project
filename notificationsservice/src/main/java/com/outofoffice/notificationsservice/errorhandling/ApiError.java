package com.outofoffice.notificationsservice.errorhandling;
import java.time.OffsetDateTime;


import org.springframework.http.HttpStatus;
import lombok.Data;

@Data
public class ApiError {
	 private HttpStatus status;
	    private String message;
	    private String error;
	    private OffsetDateTime timestamp;

	    public ApiError() {
	        super();
	    }

	    public ApiError(final HttpStatus status, final String message, final String error,OffsetDateTime timestamp) {
	        super();
	        this.status = status;
	        this.message = message;
	        this.error = error;
	        this.timestamp = timestamp;
	    }


}