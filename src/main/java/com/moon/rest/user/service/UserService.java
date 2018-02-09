package com.moon.rest.user.service;

import com.moon.rest.user.domain.User;
import com.moon.rest.user.status.UserStatus;

public interface UserService {

	public User getUserInfo(String id);

	public UserStatus postUserInfo(User user);
	
	public User putUserInfo(User user);
	
	public String deleteUserInfo(User user);
}
