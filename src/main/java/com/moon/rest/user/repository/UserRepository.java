package com.moon.rest.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moon.rest.user.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

	public User findByEmail(String email);
	
}
