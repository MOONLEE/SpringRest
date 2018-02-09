package com.moon.rest.main.body;

import java.util.Calendar;

public class ErrorMessageBody {
	private Calendar timestamp;
	private String Message;
	private int status;
	private String error;
	private String exception;
	private String path;
	private String extraStatus;
	private String extraMessage;
	
	
	
	public ErrorMessageBody() {
		this.timestamp = Calendar.getInstance();
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getException() {
		return exception;
	}
	public void setException(String exception) {
		this.exception = exception;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getExtraStatus() {
		return extraStatus;
	}
	public void setExtraStatus(String extraStatus) {
		this.extraStatus = extraStatus;
	}
	public String getExtraMessage() {
		return extraMessage;
	}
	public void setExtraMessage(String extraMessage) {
		this.extraMessage = extraMessage;
	}
	public Calendar getTimestamp() {
		return timestamp;
	}
	
	
}
