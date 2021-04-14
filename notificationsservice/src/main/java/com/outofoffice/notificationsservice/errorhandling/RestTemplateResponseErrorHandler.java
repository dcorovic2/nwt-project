package com.outofoffice.notificationsservice.errorhandling;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Scanner;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.ResponseErrorHandler;


@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler  {
	
	@Override
	public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
		return (httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR
				|| httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR);
	}

	@Override
	@ExceptionHandler({ ResourceAccessException.class })
	public void handleError(ClientHttpResponse httpResponse) throws IOException {
		if (httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR) {
			 String responseAsString = toString(httpResponse.getBody());
			 throw new CustomException(responseAsString);
		} else if (httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR) {
			
			if (httpResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
				throw new NotFoundException();
			}
		}
	}
	
	String toString(InputStream inputStream) {
        Scanner s = new Scanner(inputStream).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
	
	static class CustomException extends IOException {
        public CustomException(String message) {
            super(message);
        }
    }
}