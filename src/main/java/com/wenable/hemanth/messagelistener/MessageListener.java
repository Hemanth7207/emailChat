package com.wenable.hemanth.messagelistener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.wenable.hemanth.configurations.RabbitMqConfig;
import com.wenable.hemanth.model.UserMessage;

@Component
public class MessageListener {

	
	@RabbitListener(queues=RabbitMqConfig.QUEUE)
	public void listener(UserMessage bean)
	{
		System.out.println(bean.getMessage());
	}
}
