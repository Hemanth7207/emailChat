package com.wenable.hemanth.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.wenable.hemanth.dao.UserDao;
import com.wenable.hemanth.model.User;

@Service
public class UserService implements UserDetailsService 
{
	@Autowired
	UserDao dao;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=dao.findByUsername(username);
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),new ArrayList<>());
	}
	
	public User add(User bean, boolean check) {
		
		User user=null;
		if(bean.getPassword() != null)
		 {
			 if(check)
			 {
				 throw new ResponseStatusException(
			                HttpStatus.CONFLICT, "User with given username already exists");
			 }
			 else
			 {
				 user= dao.add(bean);
				 
			 }
		 }
		 else
		 {
			 throw new ResponseStatusException(
		                HttpStatus.CONFLICT, "password cannot be null");
		 }
		 
		return user;
	}
	public boolean existByEmailAndPassword(String email, String password) {
		
		return dao.existByEmailAndPassword(email,password);
	}
	public void ispresent(User bean,boolean check) {
		
		
		if(bean.getPassword() != null && bean.getEmail()!= null)
		 {
			 if(check)
			 {
				 System.out.println("login Successfull");
			 }
			 else
			 {
				 throw new ResponseStatusException(
			                HttpStatus.CONFLICT, "Invalid Credentials");
			 }
			
		 }
		 else
		 {
			 throw new ResponseStatusException(
		                HttpStatus.CONFLICT, "password and email cannot be null");
		 }
	}

	public boolean existByUsername(String username) {
		
		return dao.existsByUsername(username);
	}

	public List<User> getAllUsers() {
		
		return dao.getAllUsers();
	}

	public void deleteUser(String id) {
		
		dao.deleteUser(id);
		
	}

	public User updateUser(String id, User bean) {
		
		User user=getOneUser(id);
		if(user!=null) 
		{
			if(bean.getPassword()!=null)
			{
				user.setPassword(bean.getPassword());
			}
		}
		
		return dao.updateUser(user);
	}

	public User getOneUser(String id) {
		
		return dao.getOneUser(id);
	}

	

	


}
