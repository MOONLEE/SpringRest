package com.moon.rest.user.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.moon.rest.main.exception.UserException;
import com.moon.rest.main.utiil.CryptUtil;
import com.moon.rest.user.code.UserStatus;
import com.moon.rest.user.domain.User;
import com.moon.rest.user.repository.UserRepository;

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
		try {
			User existUser = userRepository.findByEmail(user.getEmail());
			if (!ObjectUtils.isEmpty(existUser)) {
				throw new UserException(UserStatus.EXIST_USER);
			}

			user.setPassword(CryptUtil.getSha512Encrpt(user.getPassword()));
			CryptUtil.getSha512Encrpt(null);
			
			userRepository.save(user);
		
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw new UserException(UserStatus.ERROR);
		}
		
		return UserStatus.CREAT_SUCCESS;
	}

	@Override
	public User putUserInfo(User user) {
		return userRepository.save(user);
	}

	@Override
	public String deleteUserInfo(User user) {
		userRepository.delete(user);
		return user.getId(); 
	}

}
