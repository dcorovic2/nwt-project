package com.outofoffice.outofoffice.errorhandling;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.hibernate.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.Path;

import org.springframework.http.HttpHeaders;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Object> handleDataNotFoundException(NotFoundException ex, WebRequest request) {
		Map<String, Object> body = new LinkedHashMap<>();
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

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> details = new ArrayList<>();
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			details.add(error.getDefaultMessage());
		}
		ApiError error = new ApiError(HttpStatus.BAD_REQUEST, ex.getFieldError().getDefaultMessage(), "Proradi pls",
				OffsetDateTime.now());
		return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
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