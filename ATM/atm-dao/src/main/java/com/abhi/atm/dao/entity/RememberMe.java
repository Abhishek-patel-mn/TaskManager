package com.abhi.atm.dao.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "persistent_logins")
public class RememberMe {
	
	@Column(name = "username", nullable = false)
	private String username;
	
	@Id
	@Column(name = "series", nullable = false)
	private String series;
	
	@Column(name = "token", nullable = false)
	private String token;
	
	@Column(name = "last_used", nullable = false)
	private Timestamp last_used;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Timestamp getLast_used() {
		return last_used;
	}

	public void setLast_used(Timestamp last_used) {
		this.last_used = last_used;
	}
}
