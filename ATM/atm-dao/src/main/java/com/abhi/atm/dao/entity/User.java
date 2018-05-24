package com.abhi.atm.dao.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author Abhishek Patel M N
 * Jan 17, 2018
 */
@Entity
@Table( name = "users" )
public class User {

	public User() {
		
	}
	
	public User(User user) {
		this.userId = user.getUserId();
		this.userName = user.getUserName();
		this.password = user.getPassword();
		this.phoneNumber = this.getPhoneNumber();
		this.email = this.getEmail();
		this.createdDate = user.getCreatedDate();
		this.role = user.getRole();
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="user_seq")
	@SequenceGenerator(
		name="user_seq",
		sequenceName="user_seq",
		allocationSize=1
	)
	@Column(name = "user_id")
	private int userId;
	
	@NotNull
	@Column(name = "user_name")
	private String userName;
	
	@NotNull
	@Column(name = "password", length = 100)
	private String password;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "phone_number")
	private int phoneNumber;
	
	@Column(name = "created_date")
	private Date createdDate;
	
	@NotNull
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name = "role_id")
	private Role role;

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
