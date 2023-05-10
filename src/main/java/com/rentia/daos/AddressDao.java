package com.rentia.daos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Component;

import com.rentia.models.Address;
import com.rentia.models.TntProperty;
import com.rentia.repositories.AddressRepository;
import com.rentia.repositories.UsersRepository;

@Component
public class AddressDao {
	
	@Autowired
	private AddressRepository addressRepository;

	public List<Address> fetchAddressDetails(String state, String city) {
		
		List<Address> adrList = null;
		/*
		 * if(null != state && null != city) { adrList =
		 * addressRepository.findAllByStateAndCity(state, city); } else if (null !=
		 * state || null != city){ adrList =
		 * addressRepository.findAllByStateOrCity(state, city); } else { adrList =
		 * (List<Address>) addressRepository.findAll(); }
		 */
		adrList = addressRepository.findByCityAndState(city, state);
	    System.out.println(adrList);
	    return adrList;
	}
	
	public Address getById(Long id) {
		System.out.println(id);
		Address address = addressRepository.findById(id).get();
		System.out.println(address);
		return address;
	}
}
