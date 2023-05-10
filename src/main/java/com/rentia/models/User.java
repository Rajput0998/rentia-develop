package com.rentia.models;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="USERS")
public class User {

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "User Name field is required !!")
	@Column(unique = true)
	private String userName;
	@NotBlank(message = "Name field is required !!")
	private String name;
	@Column(unique = true)
	private String email;
	@Column(length = 12)
	private Long acardl;
	private String jobId;
	@Column(length = 12)
	private Long mobNum;
	private String gender;
	private String role;
	@Size(min = 2,max = 20,message = "min 2 and max 20 characters are allowed !!")
	//@JsonIgnore
	private String password;
	@Column(nullable=false,updatable=false)
    @CreationTimestamp
    @JsonIgnore
	private Date creationDate;
		
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch =
	FetchType.LAZY, orphanRemoval = true)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	private List<UserAddress> adress=new ArrayList<>();
	
	public void addAddress(UserAddress adr) {
		if ( adr == null) {
			return;
		}
		else {
			adr.setUser(this);
		}
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public Long getAcardl() {
		return acardl;
	}

	public void setAcardl(Long acardl) {
		this.acardl = acardl;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public Long getMobNum() {
		return mobNum;
	}

	public void setMobNum(Long mobNum) {
		this.mobNum = mobNum;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public List<UserAddress> getAdress() { return adress; }
	 
	public void setAdress(List<UserAddress> adress) { this.adress = adress; }	 
	  	 
}
