package com.rentia.controller;

import java.time.LocalDateTime;
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

import com.rentia.models.Payment;
import com.rentia.models.Visit;
import com.rentia.services.PaymentService;

@RestController
@RequestMapping("/api/u1/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    
    @PostMapping("/doPayment")
    public ResponseEntity<Payment> createPayment(@RequestBody String paymentBody) throws Exception {
    	try{
    		Payment createdPayment = paymentService.createPayment(paymentBody);
    	     return new ResponseEntity<>(createdPayment, HttpStatus.CREATED);
		}
		catch(Exception ex){
			throw ex;
			//return new ResponseEntity<Property>(new Property(), HttpStatus.NOT_ACCEPTABLE);
		} 
       
    }
    
    @PutMapping("/update/{paymentId}")
    public ResponseEntity<Payment> updateVisit(@PathVariable Long paymentId, @RequestBody String payment) throws Exception {
    	try{
    	Payment updatedPayment = paymentService.updatePayment(paymentId, payment);
   	     return new ResponseEntity<>(updatedPayment, HttpStatus.OK);
		}
		catch(Exception ex){
			throw ex;
		} 
    }

    @GetMapping("/getPayment/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
        Payment payment = paymentService.getPaymentById(id);
        if (payment == null) {
			  return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); 
		}
        return ResponseEntity.ok(payment);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/byBookingId/{bookingId}")
    public ResponseEntity<List<Payment>> getPaymentsByBookingId(@PathVariable Long bookingId) {
        List<Payment> payments = paymentService.getPaymentsByBookingId(bookingId);
        if (payments == null) {
			  return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); 
		}
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/byBookingIdAndDate/{bookingId}")
    public ResponseEntity<List<Payment>> getPaymentsByBookingIdAndDate(
            @PathVariable Long bookingId,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endDate) {
        List<Payment> payments = paymentService.getPaymentsByBookingIdAndDate(bookingId, startDate, endDate);
        if (payments == null) {
			  return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); 
		 } 
        return ResponseEntity.ok(payments);
    }
}
