package com.levi9.model;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="tbl_user")
public class User {

	

	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	@Column(name="username")
	private String username;

	@Column(name="password")
	private String password;
	
	@Column(name="email")
	private String email;
	
	@Column(name="phoneNumber")	
	private String phoneNumber;
	
	public User() {
		super();
	}

	public User(Long id, String username, String password, String email, String phoneNumber) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
}
