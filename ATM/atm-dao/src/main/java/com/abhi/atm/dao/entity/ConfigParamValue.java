package com.abhi.atm.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "cp_value", uniqueConstraints = { @UniqueConstraint(columnNames = "cp_value_id") } )
public class ConfigParamValue {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cp_value_seq")
	@SequenceGenerator(name = "cp_value_seq", sequenceName = "cp_value_seq", allocationSize = 1)
	@Column(name = "cp_value_id", nullable = false)
	private int paramValueId;
	
	@Column(name = "cp_value", nullable = false)
	private String paramValue;

	public int getParamValueId() {
		return paramValueId;
	}

	public void setParamValueId(int paramValueId) {
		this.paramValueId = paramValueId;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

}
