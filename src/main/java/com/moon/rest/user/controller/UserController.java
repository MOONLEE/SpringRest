package com.moon.rest.user.controller;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.moon.rest.user.domain.User;
import com.moon.rest.user.service.UserService;
import com.moon.rest.user.status.UserStatus;

@RestController()
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService springRestService;
	
	@RequestMapping(method=RequestMethod.GET
			, headers = { "Accept=application/json" }
			, value="{id}")
	public User getUserInfo(@PathVariable("id") @NotNull String id) {
		springRestService.getUserInfo(id);
		
		throw new IllegalArgumentException();
	}
	
	@RequestMapping(method=RequestMethod.POST
			, headers = { "Accept=application/json" }
			)
	public UserStatus postUserInfo(@RequestBody User user) {
		return springRestService.postUserInfo(user);
	}
	
	@RequestMapping(method=RequestMethod.PUT
			, headers = { "Accept=application/json" }
			)
	public User putUserInfo(@RequestBody User user) {
		return springRestService.putUserInfo(user);
	}

	@RequestMapping(method=RequestMethod.DELETE
			, headers = { "Accept=application/json" }
			)
	public String deleteUserInfo(@RequestBody User user) {
		return springRestService.deleteUserInfo(user);
	}
	
	
}
