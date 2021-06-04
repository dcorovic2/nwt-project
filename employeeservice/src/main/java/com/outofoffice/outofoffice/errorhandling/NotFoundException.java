package com.outofoffice.outofoffice.errorhandling;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
	public NotFoundException (String id, String type, String value, String jmbg) {
        super(String.format("%s with %s %s%s not found", type, value, id,jmbg));
    }
}
