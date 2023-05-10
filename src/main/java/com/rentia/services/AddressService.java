package com.rentia.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentia.daos.AddressDao;
import com.rentia.daos.UserDao;
import com.rentia.models.Address;
import com.rentia.models.User;

@Service
public class AddressService {

	@Autowired
	private AddressDao addressDao;

	public List<Address> fetchAddressDetails(String state, String city) {
		List<Address> adrList = addressDao.fetchAddressDetails(state, city);
		return adrList;
	}
}
