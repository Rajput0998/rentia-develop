package com.rentia.services;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.rentia.daos.BookingDao;
import com.rentia.daos.PaymentDao;
import com.rentia.models.Booking;
import com.rentia.models.Payment;
import com.rentia.models.Property;
import com.rentia.models.User;
import com.rentia.models.Visit;
import com.rentia.repositories.PaymentRepository;
import com.rentia.utilities.JsonUtil;

@Service
public class PaymentService {

    @Autowired
    PaymentDao paymentDao;
    
    @Autowired
    BookingDao bookingDao;


    public Payment createPayment(String paymentBody) throws Exception {
    	try {
    		Payment payment = new Payment();
        	Booking booking = new Booking();
        	String pattern = "yyyy-MM-dd";
        	JsonNode jsonObj = JsonUtil.stringToJson(paymentBody);
        	Long bookingId = Long.parseLong(jsonObj.get("bookingId").asText());
        	BigDecimal totalAmount = new BigDecimal(jsonObj.get("totaAmount").asText());
        	BigDecimal allocatedAmount = new BigDecimal(jsonObj.get("allocatedAmount").asText());
        	BigDecimal dueAmount = totalAmount.subtract(allocatedAmount);
    		booking = bookingDao.getBookingById(bookingId);
    		//String str = "2023-06-01 09:00:00";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime paymentDate = LocalDateTime.parse(jsonObj.get("paymentDate").asText(), formatter);
            String transactionId = jsonObj.get("transactionId").asText();
            
    		if(booking == null) {
    			throw new Exception("booking is not found");
    		} else {
    			payment.setAllocatedAmount(allocatedAmount);
    			payment.setBooking(booking);
    			payment.setDueAmount(dueAmount);
    			payment.setPaymentDate(paymentDate);
    			payment.setTotalAmount(totalAmount);
    			payment.setTransactionId(transactionId);
    			return paymentDao.savePayment(payment);
    		}	
            
    	} catch (Exception ex) {
    		throw ex;
    	}
       
    }
    
    public Payment updatePayment(Long paymentId, String paymentBody) throws Exception {
    	Payment payment = paymentDao.getPaymentById(paymentId);
    	
    	if( null != payment) {
    		try {
            	Booking booking = new Booking();
            	String pattern = "yyyy-MM-dd";
                SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            	JsonNode jsonObj = JsonUtil.stringToJson(paymentBody);
            	Long bookingId = Long.parseLong(jsonObj.get("bookingId").asText());
            	BigDecimal totalAmount = new BigDecimal(jsonObj.get("totaAmount").asText());
            	BigDecimal allocatedAmount = new BigDecimal(jsonObj.get("allocatedAmount").asText());
            	BigDecimal dueAmount = totalAmount.subtract(allocatedAmount);
        		booking = bookingDao.getBookingById(bookingId);
        		//String str = "2023-06-01 09:00:00";
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime paymentDate = LocalDateTime.parse(jsonObj.get("paymentDate").asText(), formatter);
                String transactionId = jsonObj.get("transactionId").asText();
                
        		if(booking == null) {
        			throw new Exception("booking is not found");
        		} else {
        			payment.setAllocatedAmount(allocatedAmount);
        			payment.setBooking(booking);
        			payment.setDueAmount(dueAmount);
        			payment.setPaymentDate(paymentDate);
        			payment.setTotalAmount(totalAmount);
        			payment.setTransactionId(transactionId);
        			return paymentDao.savePayment(payment);
        		}	
                
        	} catch (Exception ex) {
        		throw ex;
        	}
    	}
    	return null;
    }

    public void deletePayment(Long paymentId) {
    	paymentDao.deletePayment(paymentId);
    }

    public List<Payment> getPaymentsByBookingId(Long bookingId) {
        return paymentDao.getPaymentsByBookingId(bookingId);
    }
    
    public Payment getPaymentById(Long paymentId) {
        return paymentDao.getPaymentById(paymentId);
    }
    
    public List<Payment> getPaymentsByBookingIdAndDate(Long bookingId, LocalDateTime startDate, LocalDateTime endDate) {
		
		return paymentDao.getPaymentsByBookingIdAndDate(bookingId, startDate, endDate);
	}

    
    public List<Payment> getAllPayments() {
        return paymentDao.getAllPayments();
    }

}   

