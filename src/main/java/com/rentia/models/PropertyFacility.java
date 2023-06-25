package com.rentia.models;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PROPERTY_FACILITY")
public class PropertyFacility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "property_facility_id")
    private List<Amenity> amenities = new ArrayList<>();

    @Column(name = "doublesharingprice")
    private Double doubleSharingPrice;

    @Column(name = "singlesharingprice")
    private Double singleSharingPrice;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id")
    @JsonIgnore
    private Property property;
    
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getDoubleSharingPrice() {
		return doubleSharingPrice;
	}

	public void setDoubleSharingPrice(Double doubleSharingPrice) {
		this.doubleSharingPrice = doubleSharingPrice;
	}

	public Double getSingleSharingPrice() {
		return singleSharingPrice;
	}

	public void setSingleSharingPrice(Double singleSharingPrice) {
		this.singleSharingPrice = singleSharingPrice;
	}

	public List<Amenity> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<Amenity> amenities) {
        this.amenities = amenities;
    }

    public void addAmenity(Amenity amenity) {
        this.amenities.add(amenity);
    }

    public void removeAmenity(Amenity amenity) {
        this.amenities.remove(amenity);
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }
}