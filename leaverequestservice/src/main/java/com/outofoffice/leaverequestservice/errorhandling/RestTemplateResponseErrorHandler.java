package com.outofoffice.leaverequestservice.errorhandling;

import java.io.IOException;


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
		System.out.print("USLO!!!");
		return (httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR
				|| httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR);
	}

	@Override
	@ExceptionHandler({ ResourceAccessException.class })
	public void handleError(ClientHttpResponse httpResponse) throws IOException {
		System.out.print("uslo");
		if (httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR) {
			//throw new RuntimeException("Could not connect to " + uri + ": " + e.getMessage(), e);
		} else if (httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR) {
			
			if (httpResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
				throw new NotFoundException();
			}
		}
	}
}
