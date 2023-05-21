package com.rentia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rentia.models.Address;
import com.rentia.models.Booking;
import com.rentia.models.Property;
import com.rentia.models.Visit;
import com.rentia.services.BookingService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/u1/booking-details")
public class BookingController {
	
    @Autowired
    BookingService bookingService;
	
    @PostMapping("/create")
    public ResponseEntity<Booking> createBooking(@RequestBody String body) throws Exception {
    	try{
    		 Booking savedBookingDetails = bookingService.saveBooking(body);
    	     return new ResponseEntity<>(savedBookingDetails, HttpStatus.CREATED);
		}
		catch(Exception ex){
			throw ex;
			//return new ResponseEntity<Property>(new Property(), HttpStatus.NOT_ACCEPTABLE);
		} 
       
    }
    
    @PutMapping("/update/{bookingId}")
    public ResponseEntity<Booking> updateVisit(@PathVariable Long bookingId, @RequestBody String body) throws Exception {
    	try{
    		Booking updateBookingDetails =  bookingService.updateBooking(bookingId, body);;
   	     return new ResponseEntity<>(updateBookingDetails, HttpStatus.OK);
		}
		catch(Exception ex){
			throw ex;
			//return new ResponseEntity<Property>(new Property(), HttpStatus.NOT_ACCEPTABLE);
		} 
    }

   
    @GetMapping("/getAllBooking")
    public ResponseEntity<List<Booking>> getAllBookingDetails() {
        List<Booking> bookingList = bookingService.getAllBookings();
        return new ResponseEntity<>(bookingList, HttpStatus.OK);
    }
    
    @GetMapping("/getBooking")
	public ResponseEntity<List<Booking>> getAddress(@RequestParam(value = "userName", required=false) String userName, @RequestParam(value = "propertyName", required=false) String propertyName,
			@RequestParam(value = "startDate", required=false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, @RequestParam(value = "endDate", required=false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) throws Exception {
		
		  try{
			  List<Booking> bookingList = bookingService.fetchBookingDetails(userName, propertyName, startDate, endDate); 
			  if (bookingList == null) {
				  return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); 
			  } 
			  return new ResponseEntity<List<Booking>>(bookingList, HttpStatus.OK);
		  } catch(Exception ex){
				throw ex;
				//return new ResponseEntity<Property>(new Property(), HttpStatus.NOT_ACCEPTABLE);
			} 
		 
    }
}

