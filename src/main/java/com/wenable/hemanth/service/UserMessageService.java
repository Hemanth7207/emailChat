package com.wenable.hemanth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenable.hemanth.dao.UserMessageDao;
import com.wenable.hemanth.model.UserMessage;

@Service
public class UserMessageService {

	@Autowired
	UserMessageDao dao;
	public void saveMessage(UserMessage bean) 
	{
		 dao.saveMessage(bean);
	}
	
	public void deleteByEmail(String email) {
		
		dao.deleteByEmail(email);
	}

	public UserMessage findByEmail(String email) {
		
		return dao.findByEmail(email);
	}

	public List<UserMessage> findListOfMessages(String email) {
		
		return dao.findBYEmail(email);
	}

}
