package com.rentia.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="PROPERTY")
public class Property {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Property Name field is required !!")
	@Column(unique = true)
	private String prpName;
	
	private Double price;
	
	private int avlRoom;
	
	private int totalRoom;
	
	private int totalFloors;
	
	private boolean availabilty;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	@JoinColumn(name = "aid", nullable = false)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Address address;
	
	@ElementCollection
	@CollectionTable(name = "prop_images", joinColumns = @JoinColumn(name = "property_Id"))
	@Column(name = "propImage")
	private List<String> propertyImages = new ArrayList<>();
	private String displayImage;
	
	private String detail;
	
	@OneToOne(mappedBy = "property", fetch = FetchType.LAZY, orphanRemoval = true)
	@Cascade(CascadeType.ALL)
	@JsonIgnoreProperties("property")
    private PropertyFacility propertyFacility;

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Property() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public List<String> getPropertyImages() {
		return propertyImages;
	}

	public void setPropertyImages(List<String> propertyImages) {
		this.propertyImages = propertyImages;
	}

	public String getDisplayImage() {
		return displayImage;
	}

	public void setDisplayImage(String displayImage) {
		this.displayImage = displayImage;
	}

	public PropertyFacility getPropertyFacility() {
		return propertyFacility;
	}

	public void setPropertyFacility(PropertyFacility propertyFacility) {
		this.propertyFacility = propertyFacility;
        propertyFacility.setProperty(this);
	}

	
	
	

	
	
	
}
