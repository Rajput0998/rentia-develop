package com.rentia.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentia.models.Address;
import com.rentia.models.User;
import com.rentia.services.UserService;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController 
{
    	
	@Autowired
    private UserService userService;	
	
	@PostMapping("/register")
	public ResponseEntity<User> registerUser(@RequestBody User user ) {
		
		try{
			System.out.println(user.getAdress().size());
			User l_user = userService.registerUser(user);
			String s="Hi  " + l_user.getAdress().size() + "  your Registration Process successfully completed. Now Please Login to Continue";
			return new ResponseEntity<User>(l_user, HttpStatus.OK);
         
		}
		catch(Exception ex){
			return new ResponseEntity<User>(new User(), HttpStatus.NOT_ACCEPTABLE);
		} 
}
	
	@GetMapping("/getUser/{userName}")
	public ResponseEntity<User> getBook(@PathVariable("userName") String userName) {
        User user = userService.getUserbyUserName(userName);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
}


	


