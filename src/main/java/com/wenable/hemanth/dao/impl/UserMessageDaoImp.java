package com.wenable.hemanth.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wenable.hemanth.dao.UserMessageDao;
import com.wenable.hemanth.model.UserMessage;
import com.wenable.hemanth.repositories.UserMessageRepository;

@Repository
public class UserMessageDaoImp implements UserMessageDao{

	@Autowired
	UserMessageRepository repo;
	@Override
	public void saveMessage(UserMessage bean) {
		
		repo.save(bean);
	}
	@Override
	public void deleteByEmail(String email) {
		
		repo.deleteByEmail(email);
		
	}
	@Override
	public UserMessage findByEmail(String email) {
		
		return repo.findByEmail(email);
	}
	@Override
	public List<UserMessage> findBYEmail(String email) {
		
		return repo.findAll();
	}

}
