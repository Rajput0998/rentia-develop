package com.rentia.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rentia.models.Booking;
import com.rentia.models.Visit;
import com.rentia.services.VisitService;

@RestController
@RequestMapping("/api/u1/visits")
public class VisitController {

    @Autowired
    VisitService visitService;

 

    @GetMapping("/{visitId}")
    public Visit getVisitById(@PathVariable Long visitId) {
        return visitService.getVisitById(visitId);
    }

   
    
    @PostMapping("/create")
    public ResponseEntity<Visit> createVisit(@RequestBody String visitBody) throws Exception {
    	try{
    		 Visit savedVisitDetails = visitService.createVisit(visitBody);
    	     return new ResponseEntity<>(savedVisitDetails, HttpStatus.CREATED);
		}
		catch(Exception ex){
			throw ex;
			//return new ResponseEntity<Property>(new Property(), HttpStatus.NOT_ACCEPTABLE);
		} 
       
    }

    @PutMapping("/update/{visitId}")
    public ResponseEntity<Visit> updateVisit(@PathVariable Long visitId, @RequestBody String visitBody) throws Exception {
    	try{
   		 Visit savedVisitDetails =  visitService.updateVisit(visitId, visitBody);;
   	     return new ResponseEntity<>(savedVisitDetails, HttpStatus.OK);
		}
		catch(Exception ex){
			throw ex;
			//return new ResponseEntity<Property>(new Property(), HttpStatus.NOT_ACCEPTABLE);
		} 
    }

    @DeleteMapping("/delete/{visitId}")
    public void deleteVisit(@PathVariable Long visitId) {
    	visitService.deleteVisit(visitId);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Visit>> getVisitsByUser(@PathVariable Long userId) {
    	try{
 			  List<Visit> visitList = visitService.getVisitsByUserId(userId);
 			  if (visitList == null) {
 				  return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); 
 			  } 
 			  return new ResponseEntity<List<Visit>>(visitList, HttpStatus.OK);
 		  } catch(Exception ex){
 				throw ex;
 		} 
    }
    
    @GetMapping("/property/{prpId}")
    public ResponseEntity<List<Visit>> getVisitsByProperty(@PathVariable Long prpId) {
    	try{
 			  List<Visit> visitList = visitService.getVisitsByPgId(prpId);
 			  if (visitList == null) {
 				  return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); 
 			  } 
 			  return new ResponseEntity<List<Visit>>(visitList, HttpStatus.OK);
 		  } catch(Exception ex){
 				throw ex;
 		} 
    }
    
    @GetMapping("/property")
    public ResponseEntity<List<Visit>> getVisitsByProperty(@RequestParam(value = "visitEndDate", required=false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date visitEndDate,
    		@RequestParam(value = "visitStartDate", required=false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date visitStartDate) {
    	try{
 			  List<Visit> visitList = visitService.getVisitsByDateRange(visitStartDate, visitEndDate);
 			  if (visitList == null) {
 				  return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); 
 			  } 
 			  return new ResponseEntity<List<Visit>>(visitList, HttpStatus.OK);
 		  } catch(Exception ex){
 				throw ex;
 		} 
    }
      
    
    @GetMapping("/getVisting")
   	public ResponseEntity<List<Visit>> getAddress(@RequestParam(value = "userName", required=false) String userName, @RequestParam(value = "propertyName", required=false) String propertyName,
   			@RequestParam(value = "visitDate", required=false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date visitDate) throws Exception {
   		
   		  try{
   			  List<Visit> visitList = visitService.fetchVistingDetails(userName, propertyName, visitDate); 
   			  if (visitList == null) {
   				  return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); 
   			  } 
   			  return new ResponseEntity<List<Visit>>(visitList, HttpStatus.OK);
   		  } catch(Exception ex){
   				throw ex;
   				//return new ResponseEntity<Property>(new Property(), HttpStatus.NOT_ACCEPTABLE);
   			} 
   		 
       }
    }     
