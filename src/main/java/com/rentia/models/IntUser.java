package com.rentia.models;

import java.util.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.*;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="INTUSER")
public class IntUser {

	public IntUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "Name field is required !!")
	private String name;
	//@Column(unique = true)
	private String email;
	private Double minAmount;
	private Double maxAmount;
	private String pgType;

	@Column(length = 12)
	private Long mobNum;
	
	@Column(nullable=false,updatable=false)
    @CreationTimestamp
    @JsonIgnore
	private Date creationDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Double getMinAmount() {
		return minAmount;
	}

	public void setMinAmount(Double minAmount) {
		this.minAmount = minAmount;
	}

	public Double getMaxAmount() {
		return maxAmount;
	}

	public void setMaxAmount(Double maxAmount) {
		this.maxAmount = maxAmount;
	}

	public Long getMobNum() {
		return mobNum;
	}

	public void setMobNum(Long mobNum) {
		this.mobNum = mobNum;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getPgType() {
		return pgType;
	}

	public void setPgType(String pgType) {
		this.pgType = pgType;
	}
	
	
  	
	  	 
}
