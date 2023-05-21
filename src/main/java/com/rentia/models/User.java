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
@Table(name="USERS")
public class User implements UserDetails {

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
	/*
	 * @Size(min = 2,max = 20,message =
	 * "min 2 and max 20 characters are allowed !!") //@JsonIgnore
	 */	
	private String password;
	@Column(nullable=false,updatable=false)
    @CreationTimestamp
    @JsonIgnore
	private Date creationDate;
    @ElementCollection
    @CollectionTable(name = "doc_images", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "dosImage")
    private List<String> aadharImage = new ArrayList<>();
	private String selfImage;
	
	private Boolean isAccountNonExpired;
	
	private Boolean isCredentialsNonExpired;
	
	private Boolean isAccountNonlocked;
		
	
	public Boolean getIsAccountNonExpired() {
		return isAccountNonExpired;
	}


	public void setIsAccountNonExpired(Boolean isAccountNonExpired) {
		this.isAccountNonExpired = isAccountNonExpired;
	}


	public Boolean getIsCredentialsNonExpired() {
		return isCredentialsNonExpired;
	}


	public void setIsCredentialsNonExpired(Boolean isCredentialsNonExpired) {
		this.isCredentialsNonExpired = isCredentialsNonExpired;
	}


	public Boolean getIsAccountNonlocked() {
		return isAccountNonlocked;
	}


	public void setIsAccountNonlocked(Boolean isAccountNonlocked) {
		this.isAccountNonlocked = isAccountNonlocked;
	}
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

	
	public List<String> getAadharImage() {
		return aadharImage;
	}


	public void setAadharImage(List<String> fileNames) {
		this.aadharImage = fileNames;
	}


	public String getSelfImage() {
		return selfImage;
	}


	public void setSelfImage(String selfImage) {
		this.selfImage = selfImage;
	}


	public List<UserAddress> getAdress() { return adress; }
	 
	public void setAdress(List<UserAddress> adress) { this.adress = adress; }


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(this.role);
		return List.of(simpleGrantedAuthority);
	}


	@Override
	public String getUsername() {
		return this.userName;
	}


	@Override
	public boolean isAccountNonExpired() {
		return true;
	}


	@Override
	public boolean isAccountNonLocked() {
		return true;
	}


	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}


	@Override
	public boolean isEnabled() {
		return true;
	}	
	
	
	  	 
}
