package com.wenable.hemanth.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wenable.hemanth.dao.UserDao;
import com.wenable.hemanth.model.User;
import com.wenable.hemanth.repositories.UserRepository;


@Repository
public class UserDaoImplementation implements UserDao{

	
	@Autowired
	UserRepository repo;
	@Override
	public User findByUsername(String username) {
		
		return repo.findByUsername(username);
	}
	@Override
	public User add(User bean) {
		
		return repo.save(bean);
	}
	
	@Override
	public boolean existByEmailAndPassword(String email, String password) {
		
		return repo.existsByEmailAndPassword(email,password);
	}
	@Override
	public boolean existsByUsername(String username) {
		
		return repo.existsByUsername(username);
	}
	@Override
	public List<User> getAllUsers() {
		
		return repo.findAll();
	}
	@Override
	public void deleteUser(String id) {
		
		repo.deleteById(id);
		
	}
	@Override
	public User updateUser(User user) {
		
		return repo.save(user);
	}
	@Override
	public User getOneUser(String id) {
		
		return repo.findById(id).orElseThrow();
	}
	
	
}
