package com.rentia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentia.services.RegisterPropertyService;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.rentia.models.TntProperty;
import com.rentia.models.User;


@RestController
@CrossOrigin
@RequestMapping("/property")
public class RegisterPropertyController {
	
	@Autowired
	private RegisterPropertyService registerPropertyService;

	@PostMapping(value = "/register", consumes = APPLICATION_JSON_VALUE)
	public ResponseEntity<TntProperty> registerProperty(@RequestBody String body){
		
		try {
			return new ResponseEntity<TntProperty>(registerPropertyService.registerProperty(body),HttpStatus.OK);
		}
		catch(Exception ex) {
			return new ResponseEntity<TntProperty>(new TntProperty(), HttpStatus.NOT_ACCEPTABLE);
		}
	}
	

    @PutMapping("/register/{regId}")
    public ResponseEntity<TntProperty> updateProperty(@RequestBody String body, @PathVariable("regId") Long regId) {
        try {
            //this.bookService.updateBook(book, bookId);
            return new ResponseEntity<TntProperty>(registerPropertyService.updateRegProperty(body, regId),HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<TntProperty>(new TntProperty(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    
    
    @GetMapping("/getRegisterProperty/{regId}")
	public ResponseEntity<TntProperty> getProperty(@PathVariable("regId") Long regId) {
    	TntProperty tntProperty = registerPropertyService.getregTntProperty(regId);
        if (tntProperty == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return new ResponseEntity<TntProperty>(tntProperty, HttpStatus.OK);
    }

}
