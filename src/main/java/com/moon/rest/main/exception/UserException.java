package com.moon.rest.main.exception;

import com.moon.rest.user.status.UserStatus;

public class UserException extends RuntimeException {

	private UserStatus userStatus; 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1587408128182290501L;

	
	public UserException() {
		this.userStatus = UserStatus.ERROR;
	}
	
	public UserException(UserStatus userStatus) {
		this.userStatus = userStatus;
	}

	public UserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.userStatus = UserStatus.ERROR;
	}

	public UserException(String message, Throwable cause) {
		super(message, cause);
		this.userStatus = UserStatus.ERROR;
	}

	public UserException(String message) {
		super(message);
		this.userStatus = UserStatus.ERROR;
	}

	public UserException(Throwable cause) {
		super(cause);
		this.userStatus = UserStatus.ERROR;
	}

	public UserStatus getUserStatus() {
		return userStatus;
	}


	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}
}
