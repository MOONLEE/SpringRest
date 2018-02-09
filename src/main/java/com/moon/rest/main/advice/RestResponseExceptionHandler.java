package com.moon.rest.main.advice;

import java.util.Iterator;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moon.rest.main.body.ErrorMessageBody;
import com.moon.rest.main.exception.UserException;

/**
 * @author moonlee
 *
 */
@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {
 

	private static final Log LOG = LogFactory.getLog(RestResponseExceptionHandler.class);
	
	
	@ExceptionHandler(value = {UserException.class})
	public ResponseEntity<Object> handleConflict(UserException ex, WebRequest request, HttpServletResponse response) {
		
		// Error Message Body
		ErrorMessageBody body = new ErrorMessageBody();
		body.setStatus(HttpStatus.CONFLICT.value());
		body.setError(HttpStatus.CONFLICT.getReasonPhrase());
		body.setException(ex.getClass().getName());
		body.setMessage(ex.getUserStatus().getMessage());
		body.setExtraStatus(ex.getUserStatus().getStatus());
		body.setPath(request.getContextPath());
		
		ObjectMapper om = new ObjectMapper();
		String bodyOfResponse = "";
		
		// Passing Error
		try {
			bodyOfResponse = om.writeValueAsString(body);
		} catch (JsonProcessingException e) {
			LOG.error(e.getMessage(), e);
		}
		
		return this.handleExceptionInternal(ex, bodyOfResponse, makeErrorHeader(response) , HttpStatus.CONFLICT, request);
	}
	
	
	
	@ExceptionHandler(value = {NullPointerException.class})
	@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Test Conflict Error")
	public void handleIligal() {
		// Nothing to do
	}
	
	
	private HttpHeaders makeErrorHeader(HttpServletResponse response) {
		HttpHeaders header = new HttpHeaders();
		Iterator<String> headerNames = response.getHeaderNames().iterator();
		while (headerNames.hasNext()) {
			String name = headerNames.next();
			header.set(name, response.getHeader(name));
		}
		return header;
	}
	
}
