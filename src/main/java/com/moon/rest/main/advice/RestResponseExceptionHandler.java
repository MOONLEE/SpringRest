package com.moon.rest.main.advice;

import java.util.Iterator;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.moon.rest.main.exception.UserException;

@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {
 

	@ExceptionHandler(value = {UserException.class})
	public ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request, HttpServletResponse response) {
		String bodyOfResponse = "conflict";
		
		
		return this.handleExceptionInternal(ex, bodyOfResponse, makeNewHeader(response) , HttpStatus.CONFLICT, request);
	}
	
	@ExceptionHandler(value = {NullPointerException.class})
	@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Test Conflict Error")
	public void handleIligal() {
	}
	
	
	private HttpHeaders makeNewHeader(HttpServletResponse response) {
		HttpHeaders header = new HttpHeaders();
		Iterator<String> headerNames = response.getHeaderNames().iterator();
		while (headerNames.hasNext()) {
			String name = headerNames.next();
			header.set(name, response.getHeader(name));
		}
		return header;
	}
}
