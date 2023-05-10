package com.rentia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rentia.models.Address;
import com.rentia.models.User;
import com.rentia.services.AddressService;
import com.rentia.services.UserService;

@CrossOrigin
@RestController
@RequestMapping("/address")
public class AddressController {
	
	@Autowired
    private AddressService addressService;
	
	@GetMapping("/getAddress/{country}")
	public ResponseEntity<List<Address>> getAddress(@PathVariable("country") String country, @RequestParam(value = "state", required=false) String state,
			@RequestParam(value = "city", required=false) String city) {
		
		  System.out.println("city" + city + "state" + state );
		
		  List<Address> adr = addressService.fetchAddressDetails(state, city); 
		  if (adr == null) {
			  return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); 
		  } 
		  return new ResponseEntity<List<Address>>(adr, HttpStatus.OK);
		 
    }

}
