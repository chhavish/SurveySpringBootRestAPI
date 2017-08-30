package com.survey.springboot.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.survey.springboot.model.Users;


public interface UserService {
	
	Users findById(long id);
	
	Users findByName(String name);
	
	void saveUser(Users user);
	
	void updateUser(Users user);
	
	void deleteUserById(long id);

	List<Users> findAllUsers();
	
	void deleteAllUsers();
	
	boolean isUserExist(Users user);
	
}
