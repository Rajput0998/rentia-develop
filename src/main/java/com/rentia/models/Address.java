package com.rentia.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="ADDRESS")
public class Address {

	
    

	/*
	 * public Address(String state, String city, int pin) { super(); this.state =
	 * state; this.city = city; this.pin = pin; }
	 * 
	 * public Address() { super(); // TODO Auto-generated constructor stub }
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long adrid;
	
	private String state;
	
	private String city;
	
	private String street;
	
	private int pin;
	
	private String country;

	@Column(length = 50)
	private String other;
	

	public Long getAdrid() {
		return adrid;
	}

	public void setAdrid(Long adrid) {
		this.adrid = adrid;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}


	@Override
	public String toString() {
		return "Address [adrid=" + adrid + ", state=" + state + ", city=" + city + ", street=" + street + ", pin=" + pin
				+ ", country=" + country + ", other=" + other + "]";
	}

	

}
