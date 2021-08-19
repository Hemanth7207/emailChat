package com.wenable.hemanth.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.wenable.hemanth.model.UserMessage;

public interface UserMessageRepository extends MongoRepository<UserMessage, String>
{

	void deleteByEmail(String email);

	UserMessage findByEmail(String email);

}
