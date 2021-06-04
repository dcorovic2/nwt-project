package com.outofoffice.outofoffice.errorhandling;
import java.time.OffsetDateTime;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import org.springframework.http.HttpHeaders;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Object> handleDataNotFoundException(NotFoundException ex, WebRequest request) {
		ApiError apierror = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage(), "There is no data for given input",
				OffsetDateTime.now());
		return new ResponseEntity<>(apierror, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NotValidParamException.class)
	public ResponseEntity<Object> handleNotValidParams(NotValidParamException ex, WebRequest request) {
		ApiError apierror = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage(), "Validation", OffsetDateTime.now());
		return new ResponseEntity<>(apierror, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NotSucesfullException.class)
	public ResponseEntity<Object> handleNotSuccesful(NotSucesfullException ex, WebRequest request) {
		ApiError apierror = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage(), "Something went wrong",
				OffsetDateTime.now());
		return new ResponseEntity<>(apierror, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NoDataException.class)
	public ResponseEntity<Object> handleNoData(NoDataException ex, WebRequest request) {
		ApiError apierror = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage(), "No data in DB", OffsetDateTime.now());
		return new ResponseEntity<>(apierror, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
		String error = "";
		for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
			error +=violation.getMessage()+ ". ";
		}
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST,error , "Not valid!", OffsetDateTime.now());
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());

	}
}