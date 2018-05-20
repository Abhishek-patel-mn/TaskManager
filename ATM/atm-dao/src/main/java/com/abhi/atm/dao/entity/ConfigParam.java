package com.abhi.atm.dao.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "config_param", uniqueConstraints = { @UniqueConstraint(columnNames = "cp_id") })
public class ConfigParam {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cp_seq")
	@SequenceGenerator(name = "cp_seq", sequenceName = "cp_seq", allocationSize = 1)
	@Column(name = "cp_id", nullable = false)
	private int paramId;
	
	@Column(name = "cp_name", nullable = false)
	private String paramName;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "cp_id")
	private List<ConfigParamValue> paramValues;

	public int getParamId() {
		return paramId;
	}

	public void setParamId(int paramId) {
		this.paramId = paramId;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public List<ConfigParamValue> getParamValues() {
		return paramValues;
	}

	public void setParamValues(List<ConfigParamValue> paramValues) {
		this.paramValues = paramValues;
	}

}
