package com.moon.rest.main.exception;

import com.moon.rest.user.code.UserStatus;

public class UserException extends RuntimeException {

	private UserStatus userStatus = UserStatus.ERROR; 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1587408128182290501L;

	
	public UserException(UserStatus userStatus) {
		this.userStatus = userStatus;
	}


	public UserStatus getUserStatus() {
		return userStatus;
	}


	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}
}
