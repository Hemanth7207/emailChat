package com.wenable.hemanth.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wenable.hemanth.model.AuthRequest;
import com.wenable.hemanth.model.User;
import com.wenable.hemanth.service.UserService;
import com.wenable.hemanth.util.TokenUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api

public class UserController {

	
	@Autowired
	UserService service;
	@Autowired
	TokenUtil tokenUtil;
	
	@Autowired
	AuthenticationManager authenticationManager;

	
	
	 @ApiOperation(value = "This method is used to generate token")
	 @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	 public String genrateToken(@RequestBody AuthRequest authRequest) throws Exception
	 	{
	 		try {
	 		 authenticationManager.authenticate(
	                  new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword())
	          );
	 		}
	 		catch(Exception e)
	 		{
	 			throw new Exception("inavalid username/password");
	 		}
	 	
	 	
	 	 return tokenUtil.generateToken(authRequest.getUsername());
	 	}
	 
	 @ApiOperation(value = "This method is used to register")
	 @RequestMapping(value = "/user/register", method = RequestMethod.POST)
	 public ResponseEntity<User> registerUser(@RequestBody User bean)
	   {
		   boolean check=this.service.existByUsername(bean.getUsername());
		   User user=service.add(bean,check);
		   return ResponseEntity.ok(user);
	   }
	 
	 @ApiOperation(value = "This method is used to login")
	 @RequestMapping(value = "/user/login", method = RequestMethod.POST)
	 public ResponseEntity<String> loginUser(@RequestBody User bean)
	   {
		   boolean check=this.service.existByEmailAndPassword(bean.getEmail(),bean.getPassword());
		   service.ispresent(bean,check);
		   return ResponseEntity.ok("User Login Successful!!");
	   }
	 @ApiOperation(value = "This method is used to get the all the users.")
	 @RequestMapping(value = "/user/all", method = RequestMethod.GET)
	    public ResponseEntity<List<User>> getAllUsers()
	    {
		   List<User> users= service.getAllUsers();
		   return ResponseEntity.ok(users);
	    }
	 @ApiOperation(value = "This method is used to delete the user")
     @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
     public String deleteUser(@PathVariable String id)
     {
    	 service.deleteUser(id);
    	 return "User Deleted Successfully!!";
     }
	 @ApiOperation(value = "This method is used to update the user")
     @RequestMapping(value = "/user/update/{id}", method = RequestMethod.PUT)
     public ResponseEntity<User> updateUser(@PathVariable String id,@RequestBody User bean)
     {
  	   User users= service.updateUser(id,bean);
  	   return ResponseEntity.ok(users);
  	   
     }
	 @ApiOperation(value = "This method is used to get one user by id")
     @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
     public ResponseEntity<User> getOneUser(@PathVariable String id)
     {
    	 User user=service.getOneUser(id);
    	 return ResponseEntity.ok(user);
     }
    
	 
	
	 
	
}

