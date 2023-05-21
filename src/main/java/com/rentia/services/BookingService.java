package com.rentia.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.rentia.daos.BookingDao;
import com.rentia.daos.PropertyDao;
import com.rentia.daos.UserDao;
import com.rentia.models.Booking;
import com.rentia.models.Property;
import com.rentia.models.User;
import com.rentia.repositories.BookingRepository;
import com.rentia.utilities.JsonUtil;

import java.text.SimpleDateFormat;
import java.text.ParseException;

@Service
public class BookingService {

    @Autowired
    private BookingDao bookingDao;
    
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private PropertyDao propertyDao;

    public Booking saveBooking(String bookingBody) throws Exception {
    	try {
    		Booking booking = new Booking();
        	User user = new User();
        	Property property = new Property();
        	String pattern = "yyyy-MM-dd";
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        	JsonNode jsonObj = JsonUtil.stringToJson(bookingBody);
        	String userName = jsonObj.get("userName").asText();
        	String propertyName = jsonObj.get("propertyName").asText();
            Date startDate = sdf.parse(jsonObj.get("startDate").asText());
            Date endDate = sdf.parse(jsonObj.get("endDate").asText());
        	user = userDao.getUserbyUserName(userName);
    		property = propertyDao.getPropertyByName(propertyName);
    		if(null == user || null == property ) {
    			throw new Exception("User or Property is not found");
    		} else {
    			booking.setProperty(property);
        		booking.setTenant(user);
        		booking.setEndDate(endDate);
        		booking.setStartDate(startDate);
        		return bookingDao.saveBooking(booking);
    		}	
            
    	} catch (Exception ex) {
    		throw ex;
    	}
    	
    }

    public List<Booking> getAllBookings() {
        return bookingDao.getAllBookings();
    }

	public List<Booking> fetchBookingDetails(String userName, String propertyName, Date startDate, Date endDate) throws Exception {
		
		User user = userDao.getUserbyUserName(userName);
		Property property = propertyDao.getPropertyByName(propertyName);
		List<Booking> bookingList = null;
		if(null == user || null == property ) {
			throw new Exception("User or Property is not found");
		} else {
			bookingList =  bookingDao.getBookingDetails(user.getId(), property.getId(), startDate, endDate);
		}
		return bookingList;
		
	}

	public Booking updateBooking(Long bookingId, String bookingBody) throws Exception {
		try {
    		Booking booking = bookingDao.getBookingById(bookingId);
        	User user = new User();
        	Property property = new Property();
        	String pattern = "yyyy-MM-dd";
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        	JsonNode jsonObj = JsonUtil.stringToJson(bookingBody);
        	String userName = jsonObj.get("userName").asText();
        	String propertyName = jsonObj.get("propertyName").asText();
            Date startDate = sdf.parse(jsonObj.get("startDate").asText());
            Date endDate = sdf.parse(jsonObj.get("endDate").asText());
        	user = userDao.getUserbyUserName(userName);
    		property = propertyDao.getPropertyByName(propertyName);
    		if(null == user || null == property || null == booking ) {
    			throw new Exception("User or Property is not found");
    		} else {
    			booking.setProperty(property);
        		booking.setTenant(user);
        		booking.setEndDate(endDate);
        		booking.setStartDate(startDate);
        		return bookingDao.saveBooking(booking);
    		}	
            
    	} catch (Exception ex) {
    		throw ex;
    	}
	}

}
