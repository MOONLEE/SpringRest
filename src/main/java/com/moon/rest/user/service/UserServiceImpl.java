package com.moon.rest.user.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.moon.rest.main.exception.UserException;
import com.moon.rest.main.utiil.CryptUtil;
import com.moon.rest.user.domain.User;
import com.moon.rest.user.repository.UserRepository;
import com.moon.rest.user.status.UserStatus;

@Service
public class UserServiceImpl implements UserService {

	private static final Log LOG = LogFactory.getLog(UserServiceImpl.class);
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public User getUserInfo(String id) {
		return userRepository.findOne(id);
	}

	@Override
	public UserStatus postUserInfo(User user) {
		if (!ObjectUtils.isEmpty(user.getId())) {
			throw new UserException(UserStatus.INVALID_ID);
		}
		
		
		User existUser = userRepository.findByEmail(user.getEmail());
		if (!ObjectUtils.isEmpty(existUser)) {
			throw new UserException(UserStatus.EXIST_USER);
		}
		
		try {
			user.setPassword(CryptUtil.getSha512Encrpt(user.getPassword()));
			
			userRepository.save(user);
		
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw new UserException(UserStatus.ERROR);
		}
		
		return UserStatus.CREATE_SUCCESS;
	}

	@Override
	public User putUserInfo(User user) {
		return userRepository.save(user);
	}

	@Override
	public String deleteUserInfo(User user) {
		
		User existUser = userRepository.findByEmail(user.getEmail());
		
		if (ObjectUtils.isEmpty(existUser)) {
			throw new UserException(UserStatus.NOTEXIST_USER);
		}
		
		userRepository.delete(user);
		
		return user.getEmail(); 
	}

}
