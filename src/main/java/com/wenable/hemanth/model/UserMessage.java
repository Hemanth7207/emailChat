package com.wenable.hemanth.model;

import org.springframework.data.annotation.Id;

public class UserMessage 
{
	@Id
   private String id;
   private String message;
   private String email;
   



public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getMessage() {
	return message;
}

public void setMessage(String message) {
	this.message = message;
}

@Override
public String toString() {
	return "UserMessage [message=" + message + ", email=" + email + "]";
}



   
}
