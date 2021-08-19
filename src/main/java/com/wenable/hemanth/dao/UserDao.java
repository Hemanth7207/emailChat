package com.wenable.hemanth.dao;

import java.util.List;

import com.wenable.hemanth.model.User;

public interface UserDao {

	User findByUsername(String username);

	User add(User bean);

	

	boolean existByEmailAndPassword(String email, String password);

	boolean existsByUsername(String username);

	List<User> getAllUsers();

	void deleteUser(String id);

	User updateUser(User user);

	User getOneUser(String id);

	

	
	

}
