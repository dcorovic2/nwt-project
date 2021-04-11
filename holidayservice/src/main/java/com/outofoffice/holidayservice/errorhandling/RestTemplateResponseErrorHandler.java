package com.outofoffice.holidayservice.errorhandling;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;
import java.time.OffsetDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.context.request.WebRequest;

@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {
	
    @Override
    public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
    	System.out.println("ERROR3");
    	return new DefaultResponseErrorHandler().hasError(httpResponse);
    }
     
    @ExceptionHandler(ConnectException.class)
	public ResponseEntity<Object> handleServerError(ConnectException ex, WebRequest request) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
    
    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
    	System.out.println("ERROR1");

    	if (response.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR) {
            // handle 5xx errors
            // raw http status code e.g `500`
            System.out.println(response.getRawStatusCode());

            // http status code e.g. `500 INTERNAL_SERVER_ERROR`
            System.out.println(response.getStatusCode());

        } else if (response.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR) {
            // handle 4xx errors
            // raw http status code e.g `404`
            System.out.println(response.getRawStatusCode());

            // http status code e.g. `404 NOT_FOUND`
            System.out.println(response.getStatusCode());

            // get response body
            System.out.println(response.getBody());

            // get http headers
            HttpHeaders headers = response.getHeaders();
            System.out.println(headers.get("Content-Type"));
            System.out.println(headers.get("Server"));
        }
    }

}
