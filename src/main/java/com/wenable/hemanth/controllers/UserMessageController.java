package com.wenable.hemanth.controllers;

import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wenable.hemanth.configurations.RabbitMqConfig;
import com.wenable.hemanth.model.UserMessage;
import com.wenable.hemanth.service.UserMessageService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api
public class UserMessageController {

	@Autowired
	RabbitTemplate template;
	
	@Autowired
	UserMessageService service;
	 @ApiOperation(value = "This method is used to send message")
	 @RequestMapping(value = "/publish", method = RequestMethod.POST)
		public String publishMessage(@RequestBody UserMessage bean)
		{
			template.convertAndSend(RabbitMqConfig.EXCHANGE,RabbitMqConfig.QUEUE,bean);
			service.saveMessage(bean);
			
			return "User Message Published!!!";
		}
	 @ApiOperation(value = "This method is used to delete the user messages")
     @RequestMapping(value = "/user/{email}", method = RequestMethod.DELETE)
     public void deleteUserMessage(@PathVariable String email)
     {
    	 service.deleteByEmail(email);
     }
	 @ApiOperation(value = "This method is used to get the user messages")
     @RequestMapping(value = "/user/msg/{email}", method = RequestMethod.GET)
     public ResponseEntity<String> getUserMessage(@PathVariable String email)
     {
    	UserMessage user= service.findByEmail(email);
    	return ResponseEntity.ok(user.getMessage());
     }
	 @ApiOperation(value = "This method is used to get the bulk user messages")
	 @RequestMapping(value = "/user/bulk/{email}", method = RequestMethod.GET)
     public ResponseEntity<List<UserMessage>> getUserBulkMessage(@PathVariable String email)
     {
    	List<UserMessage> user= service.findListOfMessages(email);
    	return ResponseEntity.ok(user);
     }
}
