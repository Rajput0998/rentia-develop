package com.rentia.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentia.models.Address;
import com.rentia.models.User;

@CrossOrigin
@RestController
@RequestMapping("/address")
public class AddressController {
	
	//@GetMapping("/getAddress")
	/*public ResponseEntity<Address> getAddress(@PathVariable("userName") String userName) {
		
		 * Address adr = userService.getUserbyUserName(userName); if (user == null) {
		 * return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); } return new
		 * ResponseEntity<User>(user, HttpStatus.OK);
		 
    }*/

}
