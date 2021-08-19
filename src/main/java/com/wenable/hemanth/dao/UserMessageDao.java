package com.wenable.hemanth.dao;

import java.util.List;

import com.wenable.hemanth.model.UserMessage;

public interface UserMessageDao {

	void saveMessage(UserMessage bean);

	void deleteByEmail(String email);

	UserMessage findByEmail(String email);

	List<UserMessage> findBYEmail(String email);

}
