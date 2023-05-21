package com.rentia.daos;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.rentia.models.Booking;
import com.rentia.repositories.BookingRepository;

@Component
public class BookingDao {

    @Autowired
    private BookingRepository bookingRepository;

    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
    
    public List<Booking> getAllBookingByPropertyId(Long propertyId) {
        return bookingRepository.findAllByPropertyId(propertyId);
    }
    
    public Booking getBookingById(Long bookingId) {
        return bookingRepository.findById(bookingId).get();
    }
    
    public List<Booking> getAllBookingByUserId(Long userId) {
        return bookingRepository.findByUserId(userId);
    }
    
    public List<Booking> getBookingDetails(Long userId, Long propertyId, Date startDate, Date endDate) {
        return bookingRepository.findByParameters(userId, propertyId, startDate, endDate);
    }
    
    
   

    // Other methods as per your requirements
}
