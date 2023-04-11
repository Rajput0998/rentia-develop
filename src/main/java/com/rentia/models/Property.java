package com.rentia.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PROPERTY")
public class Property {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String prpName;
	
	private Double price;
	
	private int avlRoom;
	
	private int totalRoom;
	
	private int totalFloors;
	
	private boolean availabilty;

	public Property() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPrpName() {
		return prpName;
	}

	public void setPrpName(String prpName) {
		this.prpName = prpName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public int getAvlRoom() {
		return avlRoom;
	}

	public void setAvlRoom(int avlRoom) {
		this.avlRoom = avlRoom;
	}

	public int getTotalRoom() {
		return totalRoom;
	}

	public void setTotalRoom(int totalRoom) {
		this.totalRoom = totalRoom;
	}

	public int getTotalFloors() {
		return totalFloors;
	}

	public void setTotalFloors(int totalFloors) {
		this.totalFloors = totalFloors;
	}

	public boolean isAvailabilty() {
		return availabilty;
	}

	public void setAvailabilty(boolean availabilty) {
		this.availabilty = availabilty;
	}
	
	
	
}
