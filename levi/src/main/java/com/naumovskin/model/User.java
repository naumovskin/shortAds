package com.naumovskin.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	@Column(name="firstname", nullable=false)
	private String firstname;
	
	@Column(name="lastname", nullable=false)
	private String lastname;
	
	@Column(name="username", nullable=false)
	private String username;
	
	@Column(name="email", nullable=false)
	private String email;
	
	@Column(name="phone", nullable=false)
	private String phone;
	
	@Column(name="password", nullable=false)
	private String password;
	
	@OneToMany(mappedBy="user", cascade=CascadeType.REMOVE)
	private List<Ad> myAdverts = new ArrayList<>();
	
	@Enumerated(EnumType.STRING)
	@Column(name="system_role")
	private SystemRole systemRole;
	
	public void addAdvert(Ad advert){
		this.myAdverts.add(advert);
		
		if(advert.getUser()!= this){
			advert.setUser(this);
		}
	}
	
	public void removeAdvert(Ad advert){
		advert.setUser(null);
		myAdverts.remove(advert);
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Ad> getMyAdverts() {
		return myAdverts;
	}
	public void setMyAdverts(List<Ad> myAdverts) {
		this.myAdverts = myAdverts;
	}

	public SystemRole getSystemRole() {
		return systemRole;
	}

	public void setSystemRole(SystemRole systemRole) {
		this.systemRole = systemRole;
	}

	public Long getId() {
		return id;
	}

	
}
