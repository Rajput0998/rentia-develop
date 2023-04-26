package com.rentia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rentia.models.UserAddress;

public interface AddressRepository extends JpaRepository<UserAddress, Long> {
	
}