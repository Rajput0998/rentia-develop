package com.rentia.daos;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rentia.models.Payment;
import com.rentia.repositories.PaymentRepository;

@Component
public class PaymentDao {

    @Autowired
    PaymentRepository paymentRepository;


    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public void deletePayment(Long paymentId) {
        paymentRepository.deleteById(paymentId);
    }

    public List<Payment> getPaymentsByBookingId(Long bookingId) {
        return paymentRepository.findByBookingId(bookingId);
    }
    
    public Payment getPaymentById(Long paymentId) {
        return paymentRepository.findById(paymentId).get();
    }
    
    public List<Payment> getPaymentsByBookingIdAndDate(Long bookingId, LocalDateTime startDate, LocalDateTime endDate) {
		
		return paymentRepository.findByBookingIdAndPaymentDateBetween(bookingId, startDate, endDate);
	}

    
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    // Other methods as needed
}

