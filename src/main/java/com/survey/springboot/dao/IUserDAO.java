package com.survey.springboot.dao;

import java.util.List;

import com.survey.springboot.model.Users;

public interface IUserDAO {

	Users findById(long id);

	Users findByName(String name);

	void saveUser(Users user);

	void updateUser(Users user);

	void deleteUserById(long id);

	List<Users> findAllUsers();

	void deleteAllUsers();

	boolean isUserExist(Users user);
	
	void registerUser(Users user);

}
